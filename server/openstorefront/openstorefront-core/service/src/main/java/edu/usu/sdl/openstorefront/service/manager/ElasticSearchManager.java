/*
 * Copyright 2016 Space Dynamics Laboratory - Utah State University Research Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usu.sdl.openstorefront.service.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usu.sdl.openstorefront.common.exception.OpenStorefrontRuntimeException;
import edu.usu.sdl.openstorefront.common.manager.Initializable;
import edu.usu.sdl.openstorefront.common.manager.PropertiesManager;
import edu.usu.sdl.openstorefront.common.util.Convert;
import edu.usu.sdl.openstorefront.common.util.OpenStorefrontConstant;
import edu.usu.sdl.openstorefront.common.util.StringProcessor;
import edu.usu.sdl.openstorefront.core.entity.ApprovalStatus;
import edu.usu.sdl.openstorefront.core.entity.Component;
import edu.usu.sdl.openstorefront.core.entity.ComponentAttribute;
import edu.usu.sdl.openstorefront.core.entity.ComponentReview;
import edu.usu.sdl.openstorefront.core.entity.ComponentTag;
import edu.usu.sdl.openstorefront.core.model.search.SearchSuggestion;
import edu.usu.sdl.openstorefront.core.view.ComponentSearchView;
import edu.usu.sdl.openstorefront.core.view.ComponentSearchWrapper;
import edu.usu.sdl.openstorefront.core.view.FilterQueryParams;
import edu.usu.sdl.openstorefront.core.view.SearchQuery;
import edu.usu.sdl.openstorefront.service.ServiceProxy;
import edu.usu.sdl.openstorefront.service.search.IndexSearchResult;
import edu.usu.sdl.openstorefront.service.search.SearchServer;
import edu.usu.sdl.openstorefront.service.search.SolrComponentModel;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

/**
 *
 * @author dshurtleff
 */
public class ElasticSearchManager
	implements Initializable, SearchServer
{
	private static final Logger log = Logger.getLogger(ElasticSearchManager.class.getName());

	private static final String INDEX = "openstorefront";
	private static final String INDEX_TYPE = "component";
	
	private static Client client;
	
	public static void init() 
	{
		String host = PropertiesManager.getValue(PropertiesManager.KEY_ELASTIC_HOST, "localhost");
		Integer port = Convert.toInteger(PropertiesManager.getValue(PropertiesManager.KEY_ELASTIC_PORT, "9300"));
				
		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
		} catch (UnknownHostException ex) {
			throw new OpenStorefrontRuntimeException("Unable to find elastic search server host", "Check configuration;  Check Host and Port;   property: " + PropertiesManager.KEY_ELASTIC_HOST + " current value: " + host);
		}

	}
	
	public static Client getClient() 
	{
		return client;		
	}
	
	public static void cleanup() 
	{
		if (client != null) {
			client.close();
		}
	}	
	
	@Override
	public void initialize()
	{
		ElasticSearchManager.init();
	}

	@Override
	public void shutdown()
	{
		ElasticSearchManager.cleanup();
	}

	private ServiceProxy service = ServiceProxy.getProxy();
	
	@Override
	public ComponentSearchWrapper search(SearchQuery searchQuery, FilterQueryParams filter)
	{
		ComponentSearchWrapper componentSearchWrapper = new ComponentSearchWrapper();
		
		IndexSearchResult indexSearchResult = doIndexSearch(INDEX, filter);
		
		componentSearchWrapper.setData(indexSearchResult.getSearchViews());
		componentSearchWrapper.setResults(indexSearchResult.getSearchViews().size());
		componentSearchWrapper.setTotalNumber(indexSearchResult.getTotalResults());
		
		return componentSearchWrapper;
	}

	@Override
	public IndexSearchResult doIndexSearch(String query, FilterQueryParams filter)
	{
		return doIndexSearch(query, filter, null);
	}

	@Override
	public IndexSearchResult doIndexSearch(String query, FilterQueryParams filter, String[] addtionalFieldsToReturn)
	{
		IndexSearchResult indexSearchResult = new IndexSearchResult();
		
		int maxSearchResults = 10000;
		if (filter.getMax() < maxSearchResults) {
			maxSearchResults = filter.getMax();
		}
		
		SearchResponse response = ElasticSearchManager.getClient()				
				.prepareSearch(INDEX)							
				.setQuery(QueryBuilders.queryStringQuery(query))
				.setFrom(filter.getOffset())
				.setSize(maxSearchResults)
				.addSort(filter.getSortField(), OpenStorefrontConstant.SORT_ASCENDING.equals(filter.getSortOrder()) ?  SortOrder.ASC : SortOrder.DESC)		
				.execute()
				.actionGet();		
		
		indexSearchResult.setTotalResults(response.getHits().getTotalHits());
		indexSearchResult.setMaxScore(response.getHits().getMaxScore());
		
		ObjectMapper objectMapper = StringProcessor.defaultObjectMapper();
 		for (SearchHit hit : response.getHits().getHits()) {
			try {
				ComponentSearchView view  = objectMapper.readValue(hit.getSourceAsString(), new TypeReference<ComponentSearchView>(){});
				view.setSearchScore(hit.getScore());
				indexSearchResult.getSearchViews().add(view);
				indexSearchResult.getResultsList().add(SolrComponentModel.fromComponentSearchView(view));
			} catch (IOException ex) {
				throw new OpenStorefrontRuntimeException("Unable to handle search result", "check index database", ex);
			}
		}
		return indexSearchResult;
	}

	@Override
	public List<SearchSuggestion> searchSuggestions(String query, int maxResult)
	{
		List<SearchSuggestion> searchSuggestions = new ArrayList<>();
		
		SuggestResponse response = ElasticSearchManager.getClient()
				.prepareSuggest(INDEX)				
				.setSuggestText(query)
				.execute()
				.actionGet();
		
		response.getSuggest().forEach(suggestion -> {
			//suggestion.getEntries().stream().forEach(client);
		});
		
		return searchSuggestions;
	}

	@Override
	public void index(List<Component> components)
	{
		ObjectMapper objectMapper = StringProcessor.defaultObjectMapper();
		BulkRequestBuilder bulkRequest = ElasticSearchManager.getClient().prepareBulk();
		
		//pull attribute and map
		ComponentAttribute componentAttribute = new ComponentAttribute();
		componentAttribute.setActiveStatus(ComponentAttribute.ACTIVE_STATUS);
		List<ComponentAttribute> componentAttributes = componentAttribute.findByExample();
		Map<String, List<ComponentAttribute>> attributeMap = componentAttributes.stream().collect(Collectors.groupingBy(ComponentAttribute::getComponentId));
		
		//pull reviews and map
		ComponentReview componentReview = new ComponentReview();
		componentReview.setActiveStatus(ComponentReview.ACTIVE_STATUS);
		List<ComponentReview> componentReviews = componentReview.findByExample();
		Map<String, List<ComponentReview>> reviewMap = componentReviews.stream().collect(Collectors.groupingBy(ComponentReview::getComponentId));
				
		//pull tags and map
		ComponentTag componentTag = new ComponentTag();
		componentTag.setActiveStatus(ComponentReview.ACTIVE_STATUS);
		List<ComponentTag> componentTags = componentTag.findByExample();
		Map<String, List<ComponentTag>> tagMap = componentTags.stream().collect(Collectors.groupingBy(ComponentTag::getComponentId));		
				
		for (Component component : components) {
			
			//convert to search result object
			componentAttributes = attributeMap.getOrDefault(component.getComponentId(), new ArrayList<>());
			componentReviews = reviewMap.getOrDefault(component.getComponentId(), new ArrayList<>());
			componentTags = tagMap.getOrDefault(component.getComponentId(), new ArrayList<>());
					
			ComponentSearchView componentSearchView = ComponentSearchView.toView(component, componentAttributes, componentReviews, componentTags);
			
			try {
				bulkRequest.add(ElasticSearchManager.getClient().prepareIndex(INDEX, INDEX_TYPE , componentSearchView.getComponentId())
						.setSource(objectMapper.writeValueAsBytes(componentSearchView)));
			} catch (JsonProcessingException ex) {
				log.log(Level.SEVERE, MessageFormat.format("Unable to index component: {0}  Component will be missing from search.", componentSearchView.getName()));
			}
		}
		
		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			bulkResponse.forEach(response->{
				if (StringUtils.isNotBlank(response.getFailureMessage())) {			
					log.log(Level.WARNING, MessageFormat.format("A component failed to index: {0}", response.getFailureMessage()));
				}
			});
		} else {
			log.log(Level.FINE, "Index components successfully");
		}
	}

	@Override
	public void deleteById(String id)
	{
		DeleteResponse response = client.prepareDelete(INDEX, INDEX_TYPE, id).get();
		log.log(Level.FINER, MessageFormat.format("Found Record to delete: {0}", response.isFound()));
	}

	@Override
	public void deleteAll()
	{
		//query all
		SearchResponse response = ElasticSearchManager.getClient()
				.prepareSearch()
				.execute()
				.actionGet();
		
		SearchHits searchHits = response.getHits();
		
		//bulk delete results
		BulkRequestBuilder bulkRequest = ElasticSearchManager.getClient().prepareBulk();
		searchHits.forEach(hit ->{
			bulkRequest.add(ElasticSearchManager.getClient().prepareDelete(INDEX, INDEX_TYPE, hit.getId()));		
		});
				
		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			bulkResponse.forEach(br ->{
				if (StringUtils.isNotBlank(br.getFailureMessage())) {			
					log.log(Level.WARNING, MessageFormat.format("A component failed to delete: {0}", br.getFailureMessage()));
				}
			});			
		}		
	}

	@Override
	public void saveAll()
	{
		Component component = new Component();
		component.setActiveStatus(Component.ACTIVE_STATUS);
		component.setApprovalState(ApprovalStatus.APPROVED);		
		List<Component> components = component.findByExample();

		index(components);	
	}

	@Override
	public void resetIndexer()
	{
		deleteAll();
		saveAll();		
	}
	
}
