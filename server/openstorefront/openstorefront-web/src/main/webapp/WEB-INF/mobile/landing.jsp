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
									iconCls: 'fa fa-2x fa-bars',
									scale: 'medium',
									menu: {
										items: [
											{
												text: 'User Profile',
												scale: 'medium'
											}
										]
									}
								}
							]
						}
					]
					
				});
				
				var body = Ext.create('Ext.panel.Panel', {
					region: 'center',
					scrollable: true,
					items: [
						
					],
					dockedItems: [
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
														field.up('panel').performSearch(query);
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
										this.up('panel').performSearch(query);
									}
								}
							]
						}
					]
				});
				
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
