<%--
Copyright 2016 Space Dynamics Laboratory - Utah State University Research Foundation.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="layout/toplevelLayout.jsp">
    <stripes:layout-component name="contents">
	
	<script src="scripts/component/advanceSearch.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/component/searchToolContentPanel.js?v=${appVersion}" type="text/javascript"></script>
	

	<div id="browserWarning" class="browser-warning" >
		 <p>You are using an <strong>outdated</strong> browser. Please switch to <strong>
		 <a class="browser-warning-link" href="http://www.mozilla.org/en-US/firefox/new/">Firefox</a></strong> or <strong>
		 <a class="browser-warning-link" href="https://www.google.com/intl/en-US/chrome/browser/">Chrome</a></strong>, or <strong>
		 <a class="browser-warning-link" href="http://browsehappy.com/">upgrade your browser</a></strong> to improve your experience</p>		
	</div>
	
	<script type="text/javascript">
		
		if (Ext.isIE8m) {
			Ext.get('browserWarning').setStyle({
				display: 'block'
			});
		} 		
		
		var homepage = {
			readToggleHighlight: function(highlightId) {
				
				Ext.Array.each(homepage.highlights, function(item){
					if (item.highlightId === highlightId) {
//						if (item.moreText === 'Read More >>') {
//							item.displayDesc = item.description;
//							item.moreText = '<< Read Less';
//						} else {
//							item.displayDesc = Ext.util.Format.ellipsis(Ext.util.Format.stripTags(item.description), 300);
//							item.moreText = 'Read More >>';
//						}						
//						item.tpl.overwrite(item.panel.body, item);	
					
						var articleWindow = Ext.create('Ext.window.Window', {
							title: item.title,
							width: '60%',
							height: '50%',
							autoScroll: true,
							modal: true,
							maximizable: true,
							closeAction: 'destroy',
							bodyStyle: 'padding: 20px;',
							html: item.description + '<br><br><span style="font-size: 10px;">Updated: ' + Ext.util.Format.date(item.updateDts, "m/d/y") + '</span>',							
							dockedItems: [
								{
									xtype: 'toolbar',
									dock: 'bottom',
									items: [
										{
											xtype: 'tbfill'
										},
										{
											text: 'Close',
											iconCls: 'fa fa-close',
											handler: function() {
												this.up('window').close();
											}
										},
										{
											xtype: 'tbfill'
										}
									]
								}
							]
						}).show();
					}
				});
			},
			viewRecentlyAdded: function() {
				
				var searchObj = {
					"sortField": 'approvedDts',
					"sortDirection": "ASC",
					"startOffset": 0,
					"max": 5,
					"searchElements": [{
							"searchType": "COMPONENT",
							"field": 'approvedDts',
							"value": null,
							"keyField": null,
							"keyValue": null,
							"startDate": Ext.Date.subtract(new Date(), Ext.Date.MONTH, 2),
							"endDate": null,
							"caseInsensitive": false,
							"numberOperation": "EQUALS",
							"stringOperation": "EQUALS",
							"mergeCondition": "OR"  //OR.. NOT.. AND..
						}]
				};
				
				var searchRequest = {
					type: 'Advance',
					query: searchObj
				};
				
				CoreUtil.sessionStorage().setItem('searchRequest', Ext.encode(searchRequest));
				window.location.href = 'searchResults.jsp';				
			},
			viewTopic: function(topicCode) {
				var searchObj = {
					"sortField": null,
					"sortDirection": "ASC",
					"startOffset": 0,
					"max": 2147483647,
					"searchElements": [{
							"searchType": "COMPONENT",
							"field": "componentType",
							"value": topicCode,
							"keyField": null,
							"keyValue": null,
							"startDate": null,
							"endDate": null,
							"caseInsensitive": false,
							"numberOperation": "EQUALS",
							"stringOperation": "EQUALS",
							"mergeCondition": "OR"  //OR.. NOT.. AND..
						}]
				};
				
				var searchRequest = {
					type: 'Advance',
					query: searchObj
				};
				
				CoreUtil.sessionStorage().setItem('searchRequest', Ext.encode(searchRequest));
				window.location.href = 'searchResults.jsp';				
			},
			viewCategories: function(attributeType, attributeCode) {
				var searchObj = {
					"sortField": null,
					"sortDirection": "ASC",
					"startOffset": 0,
					"max": 2147483647,
					"searchElements": [{
							"searchType": "ATTRIBUTE",
							"field": null,
							"value": null,
							"keyField": attributeType,
							"keyValue": attributeCode,
							"startDate": null,
							"endDate": null,
							"caseInsensitive": false,
							"numberOperation": "EQUALS",
							"stringOperation": "EQUALS",
							"mergeCondition": "OR"  //OR.. NOT.. AND..
						}]
				};
				
				var searchRequest = {
					type: 'Advance',
					query: searchObj
				};
				
				CoreUtil.sessionStorage().setItem('searchRequest', Ext.encode(searchRequest));
				window.location.href = 'searchResults.jsp';				
			},
			viewTag: function(tag) {
				var searchObj = {
					"sortField": null,
					"sortDirection": "ASC",
					"startOffset": 0,
					"max": 2147483647,
					"searchElements": [{
							"searchType": "TAG",
							"field": null,
							"value": tag,
							"keyField": null,
							"keyValue": null,
							"startDate": null,
							"endDate": null,
							"caseInsensitive": false,
							"numberOperation": "EQUALS",
							"stringOperation": "EQUALS",
							"mergeCondition": "OR"  //OR.. NOT.. AND..
						}]
				};
				
				var searchRequest = {
					type: 'Advance',
					query: searchObj
				};
				
				CoreUtil.sessionStorage().setItem('searchRequest', Ext.encode(searchRequest));
				window.location.href = 'searchResults.jsp';				
			}			
		};
				
		/* global Ext, CoreService, CoreApp */	
		Ext.onReady(function(){
		
			var notificationWin = Ext.create('OSF.component.NotificationWindow', {				
			});	

			var searchtoolsWin;
						
			var quoteBanner = Ext.create('Ext.panel.Panel', {
				width: '100%',				
				layout: 'center',
				cls: 'home-quote-banner-section',				
				items: [
					{
						id: 'quote',
						bodyCls: 'home-quote-banner-text',
						html: ''
					}
				]
		
			});

			var searchPanel = Ext.create('Ext.panel.Panel', {				
				width: '100%',	
				bodyCls: 'new-home-search-panel',								
				items: [
					{
						xtype: 'panel',
						width: '100%',
						layout: 'center',
						items: [
							{
								xtype: 'image',
								id: 'logoImage',
								height: 200,						
								src: ''									
							}
						]
					},					
					{
						xtype: 'panel',
						width: '100%',
						layout: 'center',
						items: [
							{
								id: 'componentStats'
							}
						]
					}														
				]				
			
			});	
			
			var searchBarPanel = Ext.create('Ext.panel.Panel', {
				width: '100%',
				layout: 'hbox',
				bodyStyle: 'background: lightgrey;padding: 20px 0px 20px 0px;',
				items: [
					{
						flex: 1
					},
					{	
						xtype: 'panel',
						width: '65%',
						layout: {
							type: 'hbox',
							align: 'stretch'
						},
						items: [
							{
								xtype: 'combobox',										
								itemId: 'searchText',
								flex: 1,
								fieldCls: 'home-search-field',
								emptyText: 'Search',
								queryMode: 'remote',
								hideTrigger: true,
								valueField: 'query',
								displayField: 'name',											
								autoSelect: false,
								store: {
									autoLoad: false,
									proxy: {
										type: 'ajax',
										url: 'api/v1/service/search/suggestions'													
									}
								},
								listeners:{
									specialkey: function(field, e) {
										var value = this.getValue();
										if (e.getKey() === e.ENTER && !Ext.isEmpty(value)) {													
											var query = value;
											if (query && !Ext.isEmpty(query)) {
												var searchRequest = {
													type: 'SIMPLE',
													query: CoreUtil.searchQueryAdjustment(query)
												};
												CoreUtil.sessionStorage().setItem('searchRequest', Ext.encode(searchRequest));
											}
											window.location.href = 'searchResults.jsp';														
										}
									}
								} 
							},
							{
								xtype: 'button',
								tooltip: 'Keyword Search',
								iconCls: 'fa fa-2x fa-search icon-search-adjustment',
								style: 'border-radius: 0px 3px 3px 0px;',																					
								width: 50,											
								handler: function(){

									var query = this.up('panel').getComponent('searchText').getValue();
									if (query && !Ext.isEmpty(query)) {																										
										var searchRequest = {
											type: 'SIMPLE',
											query: CoreUtil.searchQueryAdjustment(query)
										}
										CoreUtil.sessionStorage().setItem('searchRequest', Ext.encode(searchRequest));
									} else {
										delete CoreUtil.sessionStorage().searchRequest;
									}												
									window.location.href = 'searchResults.jsp';

								}
							},
							{
								xtype: 'button',
								text: '<span style="font-size: 10px;">Search Tools</span>',																		
								iconCls: 'fa fa-2x fa-search-plus icon-top-padding',
								iconAlign: 'top',
								margin: '0 0 0 10',
								style: 'border-radius: 3px 3px 3px 3px;',											
								width: 100,
								handler: function(){
									searchtoolsWin.show();
								}
							}
						]
					},
					{
						flex: 1
					}
				]
				
			});
			
			
			var highlightScrollerPanel = Ext.create('Ext.panel.Panel', {
				bodyCls: 'new-home-highlight-panel',
				width: '100%',
				height: 175,
				dockedItems: [
					{
						xtype: 'toolbar',
						cls: 'new-home-highlight-panel',
						dock: 'left',
						items: [
							{ xtype: 'tbfill' },
							{
								iconCls: 'fa fa-2x fa-chevron-left',
								scale: 'medium',
								handler: function(){
									homepage.highlightTask.restart();
									homepage.currentHighlighIndex--;
									updateHighlight();										
								}
							},
							{ xtype: 'tbfill' }
						]
					},
					{
						xtype: 'toolbar',
						cls: 'new-home-highlight-panel',
						dock: 'right',
						padding: '0 5 0 0',
						items: [
							{ xtype: 'tbfill' },
							{
								iconCls: 'fa fa-2x fa-chevron-right',
								scale: 'medium',
								handler: function(){
									homepage.highlightTask.restart();
									homepage.currentHighlighIndex++;
									updateHighlight('r', 'l');	
								}
							},
							{ xtype: 'tbfill' }
						]
					}					
				],
				items: [
					{
						id: 'highlightInfoPanel',
						tpl: new Ext.XTemplate(
							'<div class="new-home-highlight-item">',
							'<h2><tpl if="link"><a href="{link}" class="homelink" target="_blank">{title}</a></tpl><tpl if="!link">{title}</tpl></h2>',
							'<div class="new-home-highlight-item-desc"><tpl if="securityMarkingType">({securityMarkingType}) </tpl>{displayDesc}<br><span style="font-size: 10px;">Updated: {[Ext.util.Format.date(values.updateDts, "m/d/y")]}</span>',
							'	<span style="margin-left: 20px;"><a href="#" class="homelink" onclick="homepage.readToggleHighlight(\'{highlightId}\');">{moreText}</a></span>',
							'</div>'
						)
					}
				]
								
			});
			
		
			var loadedHighlightsRecently = false;		
		
			var updateHighlight = function(slideDirOut, slideDirIn) {
				if (homepage.currentHighlighIndex >= homepage.highlights.length) {
					homepage.currentHighlighIndex = 0;
				} 
				
				if (homepage.currentHighlighIndex < 0) {
					homepage.currentHighlighIndex = homepage.highlights.length-1;
				}

				//Ext.getCmp('highlightInfoPanel').getEl().slideOut(slideDirOut ? slideDirOut : 'l');				
				Ext.getCmp('highlightInfoPanel').update(homepage.highlights[homepage.currentHighlighIndex]);
				Ext.getCmp('highlightInfoPanel').getEl().slideIn();	
										
			};
		
			var highlights = [];
			var recently = [];
			var loadHighlightsRecently = function() {
				Ext.Ajax.request({
					url: 'api/v1/resource/highlights',
					success: function(response, opt) {
						highlights = Ext.decode(response.responseText);	
						
						Ext.Array.each(highlights, function(highlight){
							if (!highlight.link) {
								highlight.link = false;
							}
							highlight.moreText = 'Read More >>';
							highlight.displayDesc = Ext.util.Format.ellipsis(Ext.util.Format.stripTags(highlight.description), 300);
							
						});
						
						homepage.highlights = highlights;
						
						homepage.currentHighlighIndex = 0;
						Ext.getCmp('highlightInfoPanel').update(homepage.highlights[homepage.currentHighlighIndex]);
						homepage.currentHighlighIndex++;
						
						homepage.highlightTask = Ext.TaskManager.newTask({
							run: function() {
								updateHighlight();								
								homepage.currentHighlighIndex++;
							},
							interval: 10000
						});
						Ext.TaskManager.start(homepage.highlightTask);
						
						
						Ext.Ajax.request({
							url: 'api/v1/service/search/recent',
							success: function(response, opt) {
								recently = Ext.decode(response.responseText);								
								homepage.recently = recently;
								loadedHighlightsRecently = true;
								
								//populateInfoPanel(infoPanel);
							}
						});
					}
				});
			};
			loadHighlightsRecently();
		
		/*
			var populateInfoPanel = function(panel) {
				
				panel.suspendEvent('resize');
				panel.removeAll();			
				panel.resumeEvent('resize');	
				
				var layout = {
					type: 'hbox',
					align: 'stretch'
				};
				var infoPanelsWidth = '50%';
				var borderSeparator = 'border-right: 1px solid lightgrey !important; padding-right: 10px;';
				if (panel.getWidth() < 1024) {
					layout = {
						type: 'vbox'
					};
					infoPanelsWidth = '100%';
					borderSeparator = '';
				}			

				var highlightPanel = Ext.create('Ext.panel.Panel', {					
				});
				
				Ext.Array.each(highlights, function(item){
					
					var highlightTemplate = new Ext.XTemplate(
						'<div class="home-highlight-item">',
						'<h2><tpl if="link"><a href="{link}" class="link" target="_blank">{title}</a></tpl><tpl if="!link">{title}</tpl></h2>',
						'<div class="home-highlight-item-desc"><tpl if="securityMarkingType">({securityMarkingType}) </tpl>{displayDesc}<br><span style="font-size: 10px;">Updated: {[Ext.util.Format.date(values.updateDts, "m/d/y")]}</span></div>',
						'<br><div style="text-align: right;"><a href="#" class="link" onclick="homepage.readToggleHighlight(\'{highlightId}\');">{moreText}</a></div>',
						'</div>'
					);
			
					var itemPanel = Ext.create('Ext.panel.Panel', {
						tpl: highlightTemplate,
						bodyStyle: 'padding: 5px',
						margin: '0 0 20 0'
					});	
					item.tpl = highlightTemplate;
					item.moreText = 'Read More >>';
					item.displayDesc = Ext.util.Format.ellipsis(Ext.util.Format.stripTags(item.description), 300);
					item.panel = itemPanel;
					itemPanel.update(item);				
										
					highlightPanel.add(itemPanel);
					item.highlightPanel = highlightPanel;
				});
				
				var recentlyPanel = Ext.create('Ext.panel.Panel', {					
				});
				
				Ext.Array.each(recently, function(item){
					
					var template = new Ext.XTemplate(
						'<div class="home-highlight-item">',
						'	<h2><a href="#" class="link" onclick="homepage.viewRecentlyAdded(\'{componentId}\');"><tpl if="securityMarkingType">({securityMarkingType}) </tpl>{name}</a></h2>',
						'	<div class="home-highlight-item-desc">{displayDesc}</div>',
						'	<div class="home-highlight-approved">Approved: {[Ext.util.Format.date(values.addedDts, "m/d/y")]}</div>',						
						'</div>'
					);					
					
					var itemPanel = Ext.create('Ext.panel.Panel', {											
						tpl: template,
						bodyStyle: 'padding: 5px',
						margin: '0 0 20 0'
					});
					item.displayDesc = Ext.util.Format.ellipsis(Ext.util.Format.stripTags(item.description), 300);
					itemPanel.update(item);
					
					recentlyPanel.add(itemPanel);
					
				});				
				


				var mainPanel = Ext.create('Ext.panel.Panel', {					
					width: '100%',
					layout: layout,					
					items: [
						{									
							bodyStyle: borderSeparator,							
							margin: '0 10 0 0',
							xtype: 'panel',
							width: infoPanelsWidth,
							items: [
								{
									html: '<h1 class="home-info-section-title">Highlights</h1><hr class="home-info-section-title-rule">'
								},
								highlightPanel
							]									
						},
						{			
							xtype: 'panel',
							width: infoPanelsWidth,
							items: [
								{
									html: '<h1 class="home-info-section-title">Most Recently Added</h1><hr class="home-info-section-title-rule">'
								},
								recentlyPanel
							]									

						}								
					]
				});
				panel.suspendEvent('resize');
				panel.add(mainPanel);
				panel.updateLayout(true, true);
				panel.resumeEvent('resize');				
			};
	
			var infoPanel = Ext.create('Ext.panel.Panel', {				
				bodyCls: 'home-info-section',	
				width: '100%',
				listeners: {
					resize: function(panel, width, height, oldWidth, oldHeight, eOpts) {
						if (loadedHighlightsRecently) {
							populateInfoPanel(panel);
						}
					}
				}
			});
			*/
			
			var topicPanel = Ext.create('Ext.panel.Panel', {
				title: 'Topics',
				scrollable: 'horizontal',
				layout: {
					type: 'hbox',
					pack: 'center'
				}
			});
			
			Ext.Ajax.request({
				url: 'api/v1/resource/componenttypes',
				success: function(response, opts) {
					var entryTypes = Ext.decode(response.responseText);
					
					var typeMap = [
						{
							entryType: 'ARTICLE',
							iconCls: 'fa fa-5x fa-file-text new-home-section-textshadow',
							iconStyle: 'color: white;'
						},
						{
							entryType: 'DESCRIBE-CC',
							iconCls: 'fa fa-5x fa-database new-home-section-textshadow',
							iconStyle: 'color: white;'
						},
						{
							entryType: 'DESCRIBE-S',
							iconCls: 'fa fa-5x fa-cloud new-home-section-textshadow',
							iconStyle: 'color: white;'
						},
						{
							entryType: 'COMP',
							iconCls: 'fa fa-5x fa-cogs new-home-section-textshadow',
							iconStyle: 'color: #441e60;'
						},
						{
							entryType: 'DOC',
							iconCls: 'fa fa-5x fa-book new-home-section-textshadow',
							iconStyle: 'color: #006ac7;'
						}						
					];
					
					var typePanels = [];
					Ext.Array.each(entryTypes, function(entryType) {
						var icon = Ext.Array.findBy(typeMap, function(item){
							return item.entryType === entryType.componentType;
						});
						
						var panel = Ext.create('Ext.panel.Panel', {
							border: 1,				
							width: 250,							
							height: 250,
							margin: '20 20 20 20',
							tpl: new Ext.XTemplate(
								'<div class="new-home-section-header" onclick="homepage.viewTopic(\'{componentType}\');">',
								' <table style="width: 100%"><tr><td><span class="{icon.iconCls}" style="{icon.iconStyle}"></span></td>',
								' <td valign="center"><span class="home-nav-item-header">{label}</span></td></tr></table></div>',
								'<div style="padding: 10px; overflow: auto; height: 161px;">',
								' {description}',
								'</div>'
							)
						});
						entryType.icon = icon; 
						panel.update(entryType);
						typePanels.push(panel);
						
					});					
					topicPanel.add(typePanels);
				}
			});
			
			
			var categoriesPanel = Ext.create('Ext.panel.Panel', {
				title: 'Categories',
				scrollable: 'horizontal',
				layout: {
					type: 'hbox',
					pack: 'center'
				}
			});
			
			Ext.Ajax.request({
				url: 'api/v1/resource/attributes',				
				success: function(response, opts) {
					var attributes = Ext.decode(response.responseText);
					attributes.sort(function(a, b){
						return a.description.localeCompare(b.description);
					});
					
					var attributeMap = [
						{
							attributeType: 'DI2E-SVCV4-A',
							iconCls: 'fa fa-5x fa-list-ul new-home-section-textshadow',
							iconStyle: 'color: white;'
						},
						{
							attributeType: 'DI2ESTATE',
							iconCls: 'fa fa-5x fa-university new-home-section-textshadow',
							iconStyle: 'color: white;'
						},
						{
							attributeType: 'DI2ELEVEL',
							iconCls: 'fa fa-5x fa-tasks new-home-section-textshadow',
							iconStyle: 'color: white;'
						},
						{
							attributeType: 'DI2EINTENT',
							iconCls: 'fa fa-5x fa-legal new-home-section-textshadow',
							iconStyle: 'color: white;'
						},
						{
							attributeType: 'TYPE',
							iconCls: 'fa fa-5x fa-laptop new-home-section-textshadow',
							iconStyle: 'color: #441e60;'
						}						
					];					
					
					var categoryPanels = [];
					Ext.Array.each(attributes, function(attribute) {
						var icon = Ext.Array.findBy(attributeMap, function(item){
							return item.attributeType === attribute.attributeType;
						});						
						
						if (attribute.importantFlg) {
							var panel = Ext.create('Ext.panel.Panel', {
								border: 1,				
								width: 250,							
								height: 250,
								margin: '20 20 20 20',
								tpl: new Ext.XTemplate(
									'<div class="new-home-section-header">',
									' <table style="width: 100%"><tr><td><span class="{icon.iconCls}" style="{icon.iconStyle}"></span></td>',
									' <td valign="center"><span class="home-nav-item-header">{description}</span></td></tr></table></div>',
									'<div style="padding: 10px; overflow: auto; height: 161px;">',
									'<ul>',
									'<tpl for="codes">',
									'	<li><a href="#" class="link" onclick="homepage.viewCategories(\'{parent.attributeType}\', \'{code}\');">{code}</a></li>',
									'</tpl></ul></div>'
								)
							});		
							attribute.icon = icon;
							panel.update(attribute);
							categoryPanels.push(panel);
						}
					});					
					categoriesPanel.add(categoryPanels);
				}
			});			
			
			var tagPanel = Ext.create('Ext.panel.Panel', {
				title: 'Tags',
				bodyStyle: 'padding: 20px;',
				minHeight: 250,
				tpl: new Ext.XTemplate(
					'<tpl for=".">',
					' <span style="font-size: {tagSize}em; padding: 0px 10px 0px; 10px; line-height: 100%;"><a href="#" class="link" onclick="homepage.viewTag(\'{text}\');">{text}</a></span>',
					'</tpl>'
				)
			});			
			
			Ext.Ajax.request({
				url: 'api/v1/resource/components/tagviews',				
				success: function(response, opts) {
					var tags = Ext.decode(response.responseText);
					var groupedTags = [];
					Ext.Array.each(tags, function(tag) {
						var existingTag = Ext.Array.findBy(groupedTags, function(item){
							return item.text === tag.text;
						});
						if (existingTag) {
							existingTag.count++;
						} else {
							groupedTags.push({
								text: tag.text,
								count: 1
							});
						}
					});	
					groupedTags.sort(function(a, b){
						return a.text.localeCompare(b.text);
					});
					
					var maxFontSize = 5;
					var maxCount = 0;
					var minCount = 0;
					Ext.Array.each(groupedTags, function(tag) {
						if (maxCount < tag.count) {
							maxCount = tag.count;
						}
						if (minCount > tag.count) {
							minCount = tag.count;
						}
					});
					Ext.Array.each(groupedTags, function(tag) {
						var tagSize = Math.abs((maxFontSize * (tag.count - minCount)) / (maxCount - minCount));
						tag.tagSize = tagSize;
					});
					
					
					tagPanel.update(groupedTags);
				}
			});	
			
			var actionTemplate = ['<div class="new-home-section-header" onclick="{link}">',
								' <table style="width: 100%"><tr><td><span class="{iconCls}" style="{iconStyle}"></span></td>',
								' <td valign="center"><span class="home-nav-item-header">{title}</span></td></tr></table></div>',
								'<div style="padding: 10px; overflow: auto; height: 161px;">',
								' {description}',
								'</div>'];
			
			var actionPanel = Ext.create('Ext.panel.Panel', {
				title: 'Entries',
				scrollable: 'horizontal',
				layout: {
					type: 'hbox',
					pack: 'center'
				},
				items: [
					{
						border: 1,				
						width: 250,							
						height: 250,
						margin: '20 20 20 20',
						data: {
							title: 'Submit Entry',
							link: 'window.location.href=\'UserTool.action?load=Submissions\';',
							iconCls: 'fa fa-5x fa-file-text-o  new-home-section-textshadow',
							iconStyle: 'color: white;',
							description: 'Add an entry to the registry.'
						},
						tpl: actionTemplate
					},
					{
						border: 1,				
						width: 250,							
						height: 250,
						margin: '20 20 20 20',
						data: {
							title: 'Explore Relationships',
							link: 'window.location.href=\'UserTool.action?load=Relationships\';',
							iconCls: 'fa fa-5x fa-share-alt new-home-section-textshadow',
							iconStyle: 'color: orange;',							
							description: 'Vizualize relationships between entries.'
						},
						tpl: actionTemplate											
					},
					{
						border: 1,				
						width: 250,							
						height: 250,
						margin: '20 20 20 20',
						data: {
							title: 'Recently Added',
							link: 'homepage.viewRecentlyAdded();',
							iconCls: 'fa fa-5x fa-star new-home-section-textshadow',
							iconStyle: 'color: yellow;',							
							description: 'View recently added entries.'
						},
						tpl: actionTemplate																
					}
				]
			});				
			
			/*
				var next = categoriesPanel;
				var current = actionPanel.getLayout().getActiveItem();

				current.getEl().slideOut('l', {
					callback: function() {
						actionPanel.getLayout().setActiveItem(next);										
						actionPanel.getLayout().getActiveItem().getEl().slideIn('r', { duration: 1000 });
						//actionPanel.items.remove(current);
					}
				});									
				*/
			
			var actionPanel = Ext.create('Ext.tab.Panel', {	
				tabBar : {
					layout: {
						pack: 'center'
					}
				},
				bodyStyle: 'background: white;',				
				minTabWidth: 150, 
				items: [
					topicPanel,					
					categoriesPanel,					
					tagPanel,
					actionPanel
				]
			});

			var footer = Ext.create('Ext.panel.Panel', {	
				bodyCls: 'new-home-footer',
				width: '100%',
				items: [
					{
							xtype: 'panel',
							width: '100%',
							layout: 'center',
							bodyStyle: 'padding: 20px 0px 0px 0px;',							
							items: [
								{
									id: 'customFooter',
									bodyCls: 'home-footer-contents'
								}
							]
					},
					{
							xtype: 'panel',
							width: '100%',
							layout: 'center',
							bodyStyle: 'padding: 20px 0px 20px 0px;',
							items: [
								{
									html: '<div class="home-footer-version">${appVersion}</div>'
								}
							]
					}					
				]
			});

			var mainContentPanel = Ext.create('Ext.panel.Panel', {					
				items: [
					quoteBanner,
					searchPanel,
					highlightScrollerPanel,
					searchBarPanel,
					actionPanel,
					//infoPanel,
					footer
				]
			});

			Ext.create('Ext.container.Viewport', {
				layout: 'border',
				items: [{
					xtype: 'panel',
					id: 'topNavPanel',
					region: 'north',					
					border: false,					
					cls: 'border_accent',
					dockedItems: [						
						{
							xtype: 'toolbar',
							dock: 'top',								
							cls: 'nav-back-color',
							listeners: {
								resize: function(toolbar, width, height, oldWidth, oldHeight, eOpts) {
									if (width < 1024) {
										toolbar.getComponent('spacer').setHidden(true);
										toolbar.getComponent('notificationBtn').setText('');										
									} else {
										toolbar.getComponent('spacer').setHidden(false);										
										toolbar.getComponent('notificationBtn').setText('Notifications');										
									}
								}
							},
							items: [
								{
									xtype: 'tbspacer',
									itemId: 'spacer',
									width: 250
								},								
								{
									xtype: 'tbfill'
								},
								{
									xtype: 'tbtext',
									id: 'homeTitle',
									text: '',
									cls: 'page-title'
								},
								{
									xtype: 'tbfill'
								},
								{
									xtype: 'button',									
									itemId: 'notificationBtn',
									scale   : 'large',
									ui: 'default',
									iconCls: 'fa fa-2x fa-envelope icon-top-padding',
									iconAlign: 'left',
									text: 'Notifications',
									handler: function() {
										notificationWin.show();
										notificationWin.refreshData();
									}
								},								
								Ext.create('OSF.component.UserMenu', {									
									ui: 'default',
									initCallBack: function(usercontext) {
										setupServerNotifications(usercontext);	
									}
								})
							]
						}						
					]
				},
				{
					region: 'center',
					xtype: 'panel',
					scrollable: true,
					items: [
						mainContentPanel
					]
				}]
			});	
			
			CoreService.brandingservice.getCurrentBranding().then(function(response, opts){
				var branding = Ext.decode(response.responseText);
				if (branding.securityBannerText && branding.securityBannerText !== '') {
					Ext.getCmp('topNavPanel').addDocked(CoreUtil.securityBannerPanel({
						securityBannerText: branding.securityBannerText
					}), 0);
				}
			});			
			
			var loadStats = function() {
				Ext.Ajax.request({
					url: 'api/v1/service/search/stats',
					success: function(response, opts) {
						var data = Ext.decode(response.responseText);
						
					//	Ext.getCmp('componentStats').update('<div class="home-search-stats">Browse through ' + data.numberOfComponents + ' ' + branding.landingStatsText + '<div>');						
					}
					
				});
			};			
			
			var branding; 
			var loadBranding = function() {
				Ext.Ajax.request({
					url: 'api/v1/resource/branding/current',
					success: function(response, opts) {
						branding = Ext.decode(response.responseText);					
						
						Ext.getCmp('homeTitle').setText(branding.landingPageTitle);
						Ext.getCmp('quote').update('<blockquote style="text-align: center;">' + branding.landingPageBanner + '</blockquote>');
						Ext.getCmp('logoImage').setSrc(branding.primaryLogoUrl);
						Ext.getCmp('logoImage').getEl().on('load', function(evt, target, opts){
							searchPanel.updateLayout(true, true);							
						});
						
						Ext.getCmp('customFooter').update(branding.landingPageFooter);
											
						loadStats();
						
						searchtoolsWin = Ext.create('OSF.component.SearchToolWindow', {
							showTopics: false,
							showCategory: false,
							showTags: false,
							branding: branding
						});							
					}
				});
			}
			loadBranding();
			
			var checkNotifications = function(){
				Ext.Ajax.request({
					url: 'api/v1/resource/notificationevent',
					success: function(response, opts) {
						var data = Ext.decode(response.responseText);
						
						var unreadCount = 0;
						Ext.Array.each(data.data, function(item){
							if (!item.readMessage) {
								unreadCount++;
							}
						});
						
						if (unreadCount > 0) {
							Ext.toast({
								title: 'Notifications',
								html: 'You have <span style="font-size: 16px; font-weight: bold;"> ' + unreadCount + ' unread</span> notifications.',
								align: 'br',
								closable: true
							});
						}
					}
				});
				
			};
			checkNotifications();
			
			
		});
		
	</script>	
		
    </stripes:layout-component>
</stripes:layout-render>