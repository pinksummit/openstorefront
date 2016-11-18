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
/* global Ext, CoreService */

Ext.define('OSF.component.EvaluationPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'osf.widget.EvaluationPanel',
	requires: [
		'OSF.form.EvaluationInfo',
		'OSF.form.Attributes',
		'OSF.form.Relationships',
		'OSF.form.Contacts',
		'OSF.form.Resources',
		'OSF.form.Media',
		'OSF.form.Dependencies',
		'OSF.form.Metadata',
		'OSF.form.EntrySummary',
		'OSF.form.ChecklistSummary',
		'OSF.form.ChecklistQuestion',
		'OSF.form.ChecklistAll',
		'OSF.form.Section',
		'OSF.form.Review'
	],
	
	layout: 'border',
	initComponent: function () {
		this.callParent();
		
		var evalPanel = this;
		
		evalPanel.navigation = Ext.create('Ext.panel.Panel', {
			title: 'Navigation',
			iconCls: 'fa fa-navicon',
			region: 'west',
			collapsible: true,
			animCollapse: false,
			width: 250,
			minWidth: 250,
			split: true,
			scrollable: true,			
			layout: 'anchor',
			bodyStyle: 'background: white;',
			defaults: {
				width: '100%'
			},			
			items: [
				{
					xype: 'panel',
					itemId: 'evalmenu',
					title: 'Evaluation',
					titleCollapse: true,
					collapsible: true,
					margin: '20 0 0 0',
					bodyStyle: 'padding: 10px;',
					defaultType: 'button',
					defaults: {
						width: '100%',
						cls: 'evaluation-nav-button',							
						overCls: 'evaluation-nav-button-over',
						focusCls: 'evaluation-nav-button',
						margin: '5 0 0 0'
					},
					items: [
						{							
							text: 'Info',							
							handler: function(){
								evalPanel.loadContentForm({
									form: 'EvaluationInfo',
									title: 'Evaluation Info'
								});
							}
						},
						{						
							text: 'Review',														
							handler: function(){
								evalPanel.loadContentForm({
									form: 'Review',
									title: 'Review'
								});	
							}
						}						
					]
				},
				{
					xype: 'panel',
					itemId: 'entrymenu',
					title: 'Entry',	
					titleCollapse: true,
					collapsible: true,
					margin: '0 0 0 0',
					bodyStyle: 'padding: 10px;',
					defaultType: 'button',
					defaults: {
						width: '100%',
						cls: 'evaluation-nav-button',							
						overCls: 'evaluation-nav-button-over',
						focusCls: 'evaluation-nav-button',
						margin: '5 0 0 0'
					},
					items: [
						{							
							text: 'Summary',							
							handler: function(){
								evalPanel.loadContentForm({
									form: 'EntrySummary',
									title: 'Entry Summary'
								});						
							}							
						}
					]
				},
				{
					xype: 'panel',
					itemId: 'checklistmenu',
					title: 'Checklist',
					titleCollapse: true,
					collapsible: true,
					bodyStyle: 'padding: 10px;',
					margin: '0 0 0 0',
					defaultType: 'button',
					defaults: {
						width: '100%',
						cls: 'evaluation-nav-button',							
						overCls: 'evaluation-nav-button-over',
						focusCls: 'evaluation-nav-button',
						margin: '5 0 0 0'
					},					
					items: [
						{							
							text: 'Summary',							
							handler: function(){
								evalPanel.loadContentForm({
									form: 'ChecklistSummary',
									title: 'Checklist Summary'
								});
							}							
						}										
					]
				},
				{
					xype: 'panel',
					itemId: 'sectionmenu',
					title: 'Sections',
					collapsible: true,
					bodyStyle: 'padding: 10px;',
					margin: '0 0 0 0',
					defaultType: 'button',
					defaults: {
						width: '100%',
						cls: 'evaluation-nav-button',							
						overCls: 'evaluation-nav-button-over',
						focusCls: 'evaluation-nav-button',
						margin: '5 0 0 0'
					},					
					items: [
					]
				}				
				
			]
		});
		
		evalPanel.contentPanel = Ext.create('Ext.panel.Panel', {
			region: 'center',			
			layout: 'fit',
			dockedItems: [
				{
					xtype: 'toolbar',
					dock: 'top',
					itemId: 'tools',					
					cls: 'eval-form-title',
					items: [
						{
							xtype: 'tbfill'
						},
						{
							xtype: 'panel',
							itemId: 'title',							
							tpl: '<h1 style="color: white;">{title}</h1>'					
						},
						{
							xtype: 'tbfill'
						}
					]
				}
			]			
		});
		
		evalPanel.commentPanel = Ext.create('Ext.panel.Panel', {
			title: 'Comments',
			iconCls: 'fa fa-comment',
			region: 'east',
			collapsed: true,
			collapsible: true,
			animCollapse: false,
			width: 300,
			minWidth: 250,
			split: true,			
			bodyStyle: 'background: white;',
			layout: 'fit',
			items: [
				{
					xtype: 'panel',
					bodyStyle: 'padding: 10px;',
					items: [
						
					],
					dockedItems: [
						{
							xtype: 'form',
							dock: 'bottom',
							layout: 'anchor',
							items: [
								{
									xtype: 'htmleditor',
									name: 'comment',									
									width: '100%',
									fieldBodyCls: 'form-comp-htmleditor-border',
									maxLength: 4000
								}
							],
							dockedItems: [
								{
									xtype: 'toolbar',
									dock: 'bottom',
									items: [
										{
											text: 'Comment',
											iconCls: 'fa fa-comment',
											handler: function(){

											}
										},
										{
											xtype: 'tbfill'
										},
										{
											text: 'Cancel',
											hidden: true,
											iconCls: 'fa fa-close',
											handler: function(){										
											}
										}
									]
								}
							]
						}
					]
				}				
			]
		});
		
		evalPanel.add(evalPanel.navigation);
		evalPanel.add(evalPanel.contentPanel);
		evalPanel.add(evalPanel.commentPanel);
		
		CoreService.brandingservice.getCurrentBranding().then(function(response, opts){
			var branding = Ext.decode(response.responseText);			
			evalPanel.branding = branding;
		});
		
	},
	loadEval: function(evaluationId, componentId){
		var evalPanel = this;
		
		evalPanel.setLoading(true);

		
		var entryType = 'COMP';		
		Ext.Ajax.request({
			url: 'api/v1/resource/componenttypes/'+ entryType,
			callback: function() {				
			},
			success: function(response, opts) {
				var entryType = Ext.decode(response.responseText);
				var menuItems = [];
				if (entryType.dataEntryAttributes){
					menuItems.push({						
						text: 'Attributes',							
						handler: function(){
							evalPanel.loadContentForm({
								form: 'Attributes',
								title: 'Entry Attributes'
							});
						}
					});
				}
				if (entryType.dataEntryRelationships){
					menuItems.push({						
						text: 'Relationships',							
						handler: function(){
							evalPanel.loadContentForm({
								form: 'Relationships',
								title: 'Entry Relationships'
							});
						}
					});					
				}
				if (entryType.dataEntryContacts){
					menuItems.push({						
						text: 'Contacts',							
						handler: function(){
							evalPanel.loadContentForm({
								form: 'Contacts',
								title: 'Entry Contacts'
							});
						}
					});					
				}
				if (entryType.dataEntryResources){
					menuItems.push({						
						text: 'Resources',							
						handler: function(){
							evalPanel.loadContentForm({
								form: 'Resources',
								title: 'Entry Resources'
							});	
						}
					});					
				}
				if (entryType.dataEntryMedia){
					menuItems.push({						
						text: 'Media',							
						handler: function(){
							evalPanel.loadContentForm({
								form: 'Media',
								title: 'Entry Media'
							});
						}
					});						
				}
				if (entryType.dataEntryDependencies){
					menuItems.push({						
						text: 'Dependencies',							
						handler: function(){
							evalPanel.loadContentForm({
								form: 'Dependencies',
								title: 'Entry Dependencies'
							});
						}
					});					
				}				
				if (entryType.dataEntryMetadata){
					menuItems.push({						
						text: 'Metadata',							
						handler: function(){
							evalPanel.loadContentForm({
								form: 'Metadata',
								title: 'Entry Metadata'
							});
						}
					});						
				}		
				
				evalPanel.navigation.getComponent('entrymenu').add(menuItems);
				
				Ext.Ajax.request({
					url: 'api/v1/resource/evaluations/' + evaluationId +'/details',
					callback: function() {
						evalPanel.setLoading(false);	
					},
					success: function(response, opt) {
						var evaluationAll = Ext.decode(response.responseText);

						var questions = [];
						questions.push({														
							text: 'All Questions',							
							handler: function(){
								evalPanel.loadContentForm({
									form: 'ChecklistAll',
									title: 'Checklist Questions',
									data: evaluationAll.checkListAll
								});
							}							
						});
						
						Ext.Array.each(evaluationAll.checkListAll.responses, function(chkresponse) {
							questions.push({							
								text: chkresponse.question.qid,
								tooltip: chkresponse.question.question,
								handler: function(){
									evalPanel.loadContentForm({
										form: 'ChecklistQuestion',
										title: 'Checklist Question',
										data: chkresponse
									});
								}							
							});
						});	
						evalPanel.navigation.getComponent('checklistmenu').add(questions);
						
						var sections = [];
						Ext.Array.each(evaluationAll.contentSections, function(sectionAll) {
							sections.push({							
								text: sectionAll.section.title,
								handler: function(){
									evalPanel.loadContentForm({
										form: 'Section',
										title: sectionAll.section.title,
										data: sectionAll
									});
								}							
							});							
						});
						
						evalPanel.navigation.getComponent('sectionmenu').add(sections);
					}
				});				
				
			}
			
		});
		
	},
	loadContentForm: function(page) {
		var evalPanel = this;
		
		evalPanel.contentPanel.removeAll(true);
		evalPanel.contentPanel.getComponent('tools').getComponent('title').update({
			title: page.title
		});
		
		var hideSecurityMarking = true;
		if (evalPanel.branding) {
			hideSecurityMarking = !evalPanel.branding.allowSecurityMarkingsFlg;
		}
		
		var contentForm = Ext.create('OSF.form.' + page.form, Ext.apply({	
			hideSecurityMarking: hideSecurityMarking
		}, page.options)
		);

		evalPanel.contentPanel.add(contentForm);

		if (contentForm.loadData) {
			contentForm.loadData(evalPanel.evaluationId, evalPanel.componentId, page.data);
		}
		
	}
	
	
});

Ext.define('OSF.component.EvaluationFormWindow', {
	extend: 'Ext.window.Window',
	alias: 'osf.widget.EvaluationFormWindow',
	title: 'Evaluation Form',
	iconCls: 'fa fa-clipboard',
	width: '85%',
	height: '85%',
	modal: true,
	maximizable: true,
	layout: 'fit',
	initComponent: function () {
		this.callParent();
		
		var evalWin = this;
		
		evalWin.evalPanel = Ext.create('OSF.component.EvaluationPanel', {			
		});
		
		evalWin.add(evalWin.evalPanel);
		
	},
	loadEval: function(evaluationId, componentId) {
		var evalWin = this;
		
		evalWin.evalPanel.loadEval(evaluationId, componentId);
		evalWin.evalPanel.evaluationId = evaluationId;
		evalWin.evalPanel.componentId = componentId;
		
		evalWin.evalPanel.loadContentForm({
			form: 'EvaluationInfo',
			title: 'Evaluation Info'
		});		
	}
	 
});