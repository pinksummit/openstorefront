<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="../../layout/mobile.jsp">
	<stripes:layout-component name="contents">
		
		<script type="text/javascript">
			/* global Ext, CoreUtil */
			Ext.require('OSF.landing.DefaultSearch');
			
			Ext.onReady(function () {
				
				var header = Ext.create('Ext.panel.Panel', {
					region: 'north',
					cls: 'border_accent',
					dockedItems: [
						{
							xtype: 'toolbar',
							dock: 'top',
							cls: 'nav-back-color',
							items: [
								{
									xtype: 'tbtext',
									text: 'SPOON',
									cls: 'page-title'
								},
								{
									xtype: 'tbfill'
								},
								{
									iconCls: 'fa fa-2x fa-bars mobile-nav',
									scale: 'medium',
									arrowVisible: false,
									style: 'background: transparent; color: white; border: 0px solid; ',
									menu: {
										items: [
											{											
												text: '<span style="font-size: 24px;">User Profile</span>',
												iconCls: 'fa fa-2x fa-user'
											}
										]
									}
								}
							]
						}
					]
					
				});
				
				var topics = Ext.create('Ext.view.View', {
					scrollable: true,
					store: {
						autoLoad: true,
						proxy: {
							type: 'ajax',
							url: 'api/v1/resource/componenttypes'
						}
					},
					itemSelector: 'div.thumb-wrap',
					tpl: new Ext.XTemplate(
						'<tpl for=".">',
							'<div style="margin-bottom: 10px; padding: 10px; border: 1px solid darkgrey;" class="thumb-wrap">',
							' <span style="font-size: 2em;line-height: 100%">{label}</span>',
							'</div>',
						'</tpl>'
					),
					listeners: {
						itemclick: function(view, record, itemEl, index, e, opts) {
							performSearch(null, record.get('componentType'));
						}
					}
				});
				//<div sytle="text-align: center;"><i class="fa fa-2x fa-users" style:="margin-right: 10px;"></i></div>
				
				var body = Ext.create('Ext.panel.Panel', {
					region: 'center',
					layout: 'fit',
					items: [
						topics
					],
					dockedItems: [
						{
							xtype: 'panel',
							dock: 'top',
							layout: 'center',							
							items: [
								{
									xtype: 'image',
									src: 'images/di2elogo-lg.png',
									width: 375,
									height: 125
								}
							]
						},
						{
							xtype: 'panel',
							dock: 'top',
							layout: {
								type: 'hbox',
								align: 'stretch'
							},
							items: [
								{
									xtype: 'combobox',
									itemId: 'searchText',
									flex: 1,
									fieldCls: 'home-search-field-new',
									emptyText: 'Search',
									queryMode: 'remote',
									hideTrigger: true,
									valueField: 'query',
									displayField: 'name',
									autoSelect: false,	
									style: 'border-left: none;',
									store: {
										autoLoad: false,
										proxy: {
											type: 'ajax',
											url: 'api/v1/service/search/suggestions'
										},
										listeners: {
											beforeload: function (store, operation, opts) {
												store.getProxy().extraParams = {
													componentType: CoreUtil.tempComponentType ? CoreUtil.tempComponentType : null
												};
											}
										}
									},
									listeners: {

										expand: function (field, opts) {

											field.getPicker().clearHighlight();
										},

										specialkey: function (field, e) {

											var value = this.getValue();

											if (!Ext.isEmpty(value)) {

												switch (e.getKey()) {

													case e.ENTER:
														var query = value;
														performSearch(query);
														break;

													case e.HOME:
														field.setValue(field.lastQuery);
														field.selectText(0, 0);
														field.expand();
														break;

													case e.END:
														field.setValue(field.lastQuery);
														field.selectText(field.getValue().length, field.getValue().length);
														field.expand();
														break;
												}
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
									handler: function () {
										var query = this.up('panel').getComponent('searchText').getValue();
										performSearch(query);
									}
								}
							]
						},
						{
							xtype: 'panel',
							dock: 'top',
							html: '<h3>Topics</h3>'
						}
					]
				});
				
				var performSearch = function(query, entryType) {
					
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

					var searchRequest;
					var searchObj = {
						"sortField": null,
						"sortDirection": "ASC",
						"startOffset": 0,
						"max": 2147483647,
						"searchElements": searchElements
					};

					searchRequest = {
						type: 'Advance',
						query: searchObj
					};

					CoreUtil.sessionStorage().setItem('searchRequest', Ext.encode(searchRequest));

					window.location.href = 'Mobile.action?Search';
					
				};
				
				var viewport = Ext.create('Ext.container.Viewport', {
					layout: 'border',
					items: [
						header,
						body
					]
				});
				
			});
		
		</script>
	</stripes:layout-component>
</stripes:layout-render>
