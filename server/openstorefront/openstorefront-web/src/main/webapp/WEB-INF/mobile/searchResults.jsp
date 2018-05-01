<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="../../layout/mobile.jsp">
	<stripes:layout-component name="contents">
		
		<script type="text/javascript">
			/* global Ext, CoreUtil */
		
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
									iconCls: 'fa fa-2x fa-arrow-left icon-vertical-correction mobile-nav',
									style: 'background: transparent; color: white; border: 0px solid; ',
									handler: function() {
										window.location.href='Mobile.action';
									}
								},							
								{
									xtype: 'tbtext',
									text: 'Results',
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
				
				var body = Ext.create('Ext.panel.Panel', {
					region: 'center',
					layout: 'anchor',
					scrollable: true, 
					items: [						
					]
				});
				
				var viewport = Ext.create('Ext.container.Viewport', {
					layout: 'border',
					items: [
						header,
						body
					]
				});
				
				var search = function() {
					var searchRequest = CoreUtil.sessionStorage().getItem('searchRequest');
					if (searchRequest) {
						searchRequest = Ext.decode(searchRequest);
						body.setLoading('Searching...');
						Ext.Ajax.request({
							url: 'api/v1/service/search/advance?paging=true&max=50&offset=0',
							method: 'POST',
							jsonData: searchRequest.query,							
							callback: function() {
								body.setLoading(false);
							},
							success: function(response, opts){
								var results = Ext.decode(response.responseText);
								
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
									body.add(items);
								}
							}
						});
					}
				};
				search();
				
			});
		
		</script>
	</stripes:layout-component>
</stripes:layout-render>
				