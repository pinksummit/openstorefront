<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="../../layout/mobile.jsp">
	<stripes:layout-component name="contents">
		
		<script type="text/javascript">
			/* global Ext, CoreUtil */
			var componentId = '${param.componentId}';
			
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
										window.location.href='Mobile.action?Search';
									}
								},								
								{
									xtype: 'tbtext',
									text: 'Details',
									cls: 'page-title'
								},
								{
									xtype: 'tbfill'
								},
								{
									iconCls: 'fa fa-2x fa-bars mobile-nav',
									style: 'background: transparent; color: white; border: 0px solid; ',
									scale: 'medium',
									arrowVisible: false,
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
					scrollable: true, 
					bodyStyle: 'padding: 10px; background: white;',
					tpl: new Ext.XTemplate(
						'<h1>{name}</h1>',
						'<b>{componentTypeDecription}</b><br>',
						'{description}'						
					)
				});

				var viewport = Ext.create('Ext.container.Viewport', {
					layout: 'border',
					items: [
						header,
						body
					]
				});
				
				var loadDetails = function() {
					
					body.setLoading('Loading Details...');
					Ext.Ajax.request({
						url: 'api/v1/resource/components/' + componentId + '/detail',
						callback: function() {
							body.setLoading(false);
						},
						success: function(response, opts) {
							var data = Ext.decode(response.responseText);
							body.update(data);
						}
					});
				}
				loadDetails();
				
			});
		
		</script>
	</stripes:layout-component>
</stripes:layout-render>
