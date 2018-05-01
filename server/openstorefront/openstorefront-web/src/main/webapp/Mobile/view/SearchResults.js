/* 
 * Copyright 2018 Space Dynamics Laboratory - Utah State University Research Foundation.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * See NOTICE.txt for more information.
 */

/* global Ext */

Ext.define('mobile.view.SearchResults', {
	extend: 'Ext.dataview.List',
	xtype: 'searchresults',

	config: {
		title: '<span class="mobile-nav">Results</span>',		
		store: {},
		itemTpl: new Ext.XTemplate(
			'<div class="mobile-searchresult-table">',
			' <div class="mobile-searchresult-title">{name}</div>',
			' <div >',
			'  {description}',	
			' </div>',
			'</div>'
		)
	},
	performSearch: function(query, entryType) {
		var searchResultsPanel = this;
		
		searchResultsPanel.getStore().removeAll();
		
		if (!query || Ext.isEmpty(query)) {
			query = '*';
		}

		var searchElements = [
			{
				"searchType": 'INDEX',
				"field": null,
				"value": query,
				"mergeCondition": "AND"
			}
		];

		if (entryType) {
			searchElements.push({
				"searchType": "COMPONENT",
				"field": 'componentType',
				"value": entryType,
				"caseInsensitive": false,
				"numberOperation": "EQUALS",
				"stringOperation": "EQUALS",
				"mergeCondition": "AND"
			});
		}

		var searchObj = {
			"sortField": null,
			"sortDirection": "ASC",
			"startOffset": 0,
			"max": 2147483647,
			"searchElements": searchElements
		};

		searchResultsPanel.setMasked({
			xtype: 'loadmask',
			message: 'Searching...'
		});
		Ext.Ajax.request({
			url: 'api/v1/service/search/advance?paging=true&max=50&offset=0',
			method: 'POST',
			jsonData: searchObj,							
			callback: function() {
				searchResultsPanel.setMasked(false);
			},
			success: function(response, opts){
				var results = Ext.decode(response.responseText);

				searchResultsPanel.getStore().add(results.data);

				/*
				var items = [];
				Ext.Array.each(results.data, function(result) {
					items.push({
						xtype: 'panel',
						width: '100%',
						title: result.name,
						data: result,
						collapsed: true,
						collapsible: true,
						border: true,
						titleCollapse: true,
						tpl: new Ext.XTemplate(
							'{description} <button onclick="window.location.href=\'Mobile.action?Details&componentId='+result.componentId+'\';">View</button>'	
						)
					});
				});								

				if (items) {
					searchResultsPanel.add(items);
				}
				*/
			}
		});

	}
});