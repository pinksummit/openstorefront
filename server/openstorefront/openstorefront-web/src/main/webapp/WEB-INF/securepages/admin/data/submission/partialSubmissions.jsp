<%-- 
/* 
 * Copyright 2016 Space Dynamics Laboratory - Utah State University Research Foundation.
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

    Document   : entryTemplate
    Created on : Mar 21, 2016, 2:43:11 PM
    Author     : dshurtleff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-render name="../../../../../layout/toplevelLayout.jsp">
	<stripes:layout-component name="contents">

		<stripes:layout-render name="../../../../../layout/adminheader.jsp">		
		</stripes:layout-render>
				
		<script src="scripts/component/templateBlocks.js?v=${appVersion}" type="text/javascript"></script>
		<script src="scripts/component/mediaViewer.js?v=${appVersion}" type="text/javascript"></script>
		<script src="scripts/component/relationshipVisualization.js?v=${appVersion}" type="text/javascript"></script>
		<script src="scripts/component/reviewWindow.js?v=${appVersion}" type="text/javascript"></script>
		<script src="scripts/component/questionWindow.js?v=${appVersion}" type="text/javascript"></script>
		
		<div style="display:none; visibility: hidden;" id="templateHolder"></div>
		<form id="viewForm" action="Template.action?PreviewTemplate"  method="POST">		
			<input type="hidden" name="templateContents" id="viewContent" />
		</form>
		
		<script type="text/javascript">
			/* global Ext, CoreUtil */
			Ext.onReady(function(){			
				
				var templateGrid = Ext.create('Ext.grid.Panel', {
					title: 'Partial Submissions <i class="fa fa-question-circle"  data-qtip="Allows for managing partial entry submissions" ></i>',
					store: {
						fields: [
							{
								name: 'createDts',
								type:	'date',
								dateFormat: 'c'
							},								
							{
								name: 'updateDts',
								type:	'date',
								dateFormat: 'c'
							}							
						],						
						autoLoad: true,
						proxy: {
							type: 'ajax',
							url: 'api/v1/resource/usersubmissions/admin'
						}						
					},
					columnLines: true,
					columns: [						
						{ text: 'Change Request Name', dataIndex: 'componentName', flex: 1, minWidth: 200,
							renderer: function(value) {
								if (value) {
									return value;
								} else {
									return '<New Submission>'
								}
							}
						},						
						{ text: 'Entry Type', dataIndex: 'componentTypeDecription', flex: 2, minWidth: 200 },	
						{ text: 'Owner User', dataIndex: 'ownerUser', width: 150 },						
						{ text: 'Create User', dataIndex: 'createUser', width: 150 },						
						{ text: 'Create Date', dataIndex: 'createDts', width: 150, xtype: 'datecolumn', format:'m/d/y H:i:s' },						
						{ text: 'Update User', dataIndex: 'updateUser', width: 150 },						
						{ text: 'Update Date', dataIndex: 'updateDts', width: 150, xtype: 'datecolumn', format:'m/d/y H:i:s' }						
					],
					dockedItems: [
						{
							xtype: 'toolbar',
							dock: 'top',
							items: [
								{
									text: 'Refresh',
									scale: 'medium',								
									iconCls: 'fa fa-2x fa-refresh icon-button-color-refresh icon-vertical-correction',
									handler: function () {
										actionRefresh();
									}
								},
								{
									xtype: 'tbseparator'
								},
								{
									text: 'View',
									itemId: 'view',
									scale: 'medium',
									disabled: true,
									iconCls: 'fa fa-2x fa-eye icon-button-color-view icon-vertical-correction',
									handler: function () {
										actionView(templateGrid.getSelection()[0]);
									}
								}, 								
								{
									text: 'Reassign',
									itemId: 'reassign',
									scale: 'medium',
									iconCls: 'fa fa-2x fa-edit icon-button-color-edit icon-vertical-correction-edit',
									disabled: true,
									handler: function () {
										actionReassign(templateGrid.getSelection()[0]);
									}								
								},
								{
									xtype: 'tbfill'
								},
								{
									text: 'Delete',
									itemId: 'delete',
									scale: 'medium',								
									iconCls: 'fa fa-2x fa-trash icon-button-color-warning icon-vertical-correction',
									disabled: true,
									handler: function () {
										actionDelete(templateGrid.getSelection()[0]);
									}								
								}								
							]
						}
					],
					listeners: {
						itemdblclick: function(grid, record, item, index, e, opts){
							actionEdit(record);
						},
						selectionchange: function(grid, record, index, opts){
							checkEntryGridTools();
						}
					}					
				});
				
				addComponentToMainViewPort(templateGrid);				

				var checkEntryGridTools = function() {
					if (templateGrid.getSelectionModel().getCount() === 1) {
						templateGrid.queryById('reassign').setDisabled(false);
						templateGrid.queryById('view').setDisabled(false);
						templateGrid.queryById('delete').setDisabled(false);					
					} else {
						templateGrid.queryById('reassign').setDisabled(true);
						templateGrid.queryById('view').setDisabled(true);	
						templateGrid.queryById('delete').setDisabled(true);
					}
				};
				
				var actionRefresh = function() {
					templateGrid.getStore().reload();
				};

				var actionView = function(record) {					
					
					var viewSubmissionWin = Ext.create('Ext.window.Window', {
						width: '70%',
						height: '80%',
						maximizable: true,
						title: 'View',
						iconCls: 'fa fa-lg fa-eye',
						modal: true,
						layout: 'fit',
						closeAction: 'destroy',
						items: [
							{
								html: 'TODO: Show Form with the data populated'
							}
						],
						tools: [
							{
								type: 'up',
								tooltip: 'popout preview',
								handler: function(){
									//window.open('view.jsp?fullPage=true&id=' + Ext.getCmp('componentGrid').getSelection()[0].get('componentId'), "Preview");
								}
							}
						],
						dockedItems: [
							{
								xtype: 'toolbar',
								dock: 'bottom',
								items: [
									{
										text: 'Previous',
										id: 'previewWinTools-previousBtn',
										iconCls: 'fa fa-lg fa-arrow-left icon-button-color-default',
										handler: function() {
											//actionPreviewNextRecord(false);
										}
									},
									{
										xtype: 'tbfill'
									},
									{
										text: 'Close',
										iconCls: 'fa fa-lg fa-close icon-button-color-warning',
										handler: function() {
											this.up('window').hide();
										}
									},
									{
										xtype: 'tbfill'
									},
									{
										text: 'Next',
										id: 'previewWinTools-nextBtn',
										iconCls: 'fa fa-lg fa-arrow-right icon-button-color-default',
										iconAlign: 'right',
										handler: function() {
											//actionPreviewNextRecord(true);
										}
									}
								]
							}
						]
					});		
					viewSubmissionWin.show();
					
				};

				var actionReassign = function(record) {
					
					var reassignSubmissionWin = Ext.create('Ext.window.Window', {
						width: 400,
						height: 200,				
						title: 'Reassign Entry Submission',
						iconCls: 'fa fa-lg fa-edit',
						modal: true,
						layout: 'fit',
						closeAction: 'destroy',
						items : [
							{
								xtype: 'form',
								itemId: 'form',
								bodyStyle: 'padding: 10px;',
								layout: 'anchor',
								items: [
									{
										xtype: 'UserSingleSelectComboBox',
										fieldLabel: 'Select a user<span class="field-required" />',
										allowBlank: false,
										name: 'ownerUsername',
										width: '100%'
									}
								],
								dockedItems: [
									{
										xtype: 'toolbar',
										dock: 'bottom',
										items:[
											{
												text: 'Save',
												formBind: true,
												iconCls: 'fa fa-lg fa-save icon-button-color-save',
												handler: function() {
													var form = this.up('form');
													var data = form.getValues();

													reassignSubmissionWin.setLoading('Reassigning...');
													Ext.Ajax.request({
														url: 'api/v1/resource/usersubmissions/' + record.get('userSubmissionId') + '/reassignowner/' + encodeURIComponent(data.ownerUsername),
														method: 'PUT',
														callback: function() {
															reassignSubmissionWin.setLoading(false);
														},
														success: function() {
															Ext.toast('Successfully reassigned submisssion');
															actionRefresh();
														}
													})

												}
											},
											{
												xtype: 'tbfill'
											},
											{
												text: 'Close',
												iconCls: 'fa fa-lg fa-close icon-button-color-warning',
												handler: function() {
													reassignSubmissionWin.close();
												}
											}									
										]
									}
								]								
							}
						]						
					});
					reassignSubmissionWin.show();
					
					//load record
					if (record) {
						reassignSubmissionWin.queryById('form').loadRecord(record);
					}
				};

				var actionDelete = function(record){
					Ext.Msg.show({
						title:'Delete Submission?',
						iconCls: 'fa fa-lg fa-warning icon-small-vertical-correction',
						message: 'Are you sure you want to delete ' + Ext.util.Format.ellipsis(record.get('name'), 20) + '?',
						buttons: Ext.Msg.YESNOCANCEL,
						icon: Ext.Msg.QUESTION,
						fn: function(btn) {
							if (btn === 'yes') {
								Ext.getCmp('templateGrid').setLoading("Deleting...");
								Ext.Ajax.request({
									url: 'api/v1/resource/usersubmissions/' + record.get('userSubmissionId'),
									method: 'DELETE',
									callback: function(){
										Ext.getCmp('templateGrid').setLoading(false);
									},
									success: function(response, opts){
										actionRefresh();
									}
								});
							}
						}
					});
				};
				

			});

			
		</script>	
		
	</stripes:layout-component>
</stripes:layout-render>