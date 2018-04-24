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

Ext.define('OSF.customSubmission.SubmissionFormFullControl', {
	extend: 'Ext.panel.Panel',
	requires: [
		'OSF.customSubmission.SubmissionForm'
	],

	layout: 'fit',
	items: [
		{
			xtype: 'osf-submissionform',
			itemId: 'submissionForm'
		}
	],	
	dockedItems: [
		{
			xtype: 'toolbar',
			dock: 'top',
			items: [
				{
					xtype: 'tbfill'
				},
				{
					text: 'Jump To Section',
					itemId: 'sectionNav',
					scale: 'medium',
					menu: {						
					}
				}
			]
		},
		{
			xtype: 'toolbar',
			dock: 'bottom',
			items: [
				{
					text: 'Previous',
					itemId: 'previousSection',
					iconCls: 'fa fa-2x fa-arrow-left icon-vertical-correction icon-button-color-default',
					scale: 'medium',
					disabled: true,
					handler: function() {
						var submissionFormFullControl = this.up('panel');
						var form = submissionFormFullControl.queryById('submissionForm');
						form.previousSection();
						submissionFormFullControl.checkNextPrevious();
					}
				},
				{
					xtype: 'tbfill'
				},
				{
					text: 'Save',
					itemId: 'save',
					iconCls: 'fa fa-2x fa-save icon-vertical-correction icon-button-color-save',
					scale: 'medium',
					handler: function() {
						
					}
				},
				{
					text: 'Submit For Approval',
					itemId: 'submitApproval',
					iconCls: 'fa fa-2x fa-check-square-o icon-vertical-correction icon-button-color-save',
					scale: 'medium',
					hidden: true,
					handler: function() {
						
					}
				},				
				{
					xtype: 'tbfill'
				},
				{
					text: 'Next',
					itemId: 'nextSection',
					iconCls: 'fa fa-2x fa-arrow-right  icon-vertical-correction icon-button-color-default',
					scale: 'medium',
					handler: function() {
						var submissionFormFullControl = this.up('panel');
						var form = submissionFormFullControl.queryById('submissionForm');
						form.nextSection();	
						submissionFormFullControl.checkNextPrevious();
					}					
				}
			]
		}
	],
	initComponent: function () {
		var submissionFormFullControl = this;
		submissionFormFullControl.callParent();		
		
	},
	
	remoteLoad: function(submissionTemplateId, entryType, userSubmissionId) {
		var submissionFormFullControl = this;
		
		submissionFormFullControl.setLoading(true);
		var form = submissionFormFullControl.queryById('submissionForm');
		submissionFormFullControl.handleInitEvent();
		form.remoteLoadTemplate(submissionTemplateId, entryType, userSubmissionId);
		

	},
	
	checkNextPrevious : function() {
		var submissionFormFullControl = this;
		var form = submissionFormFullControl.queryById('submissionForm');		
		submissionFormFullControl.queryById('previousSection').setDisabled(true);					
		submissionFormFullControl.queryById('nextSection').setDisabled(true);

		if (form.hasPreviousSection()) {
			submissionFormFullControl.queryById('previousSection').setDisabled(false);					
		}
		if (form.hasNextSection()) {
			submissionFormFullControl.queryById('nextSection').setDisabled(false);					
		}		
	},
	
	handleInitEvent: function() {
		var submissionFormFullControl = this;
		var form = submissionFormFullControl.queryById('submissionForm');
		if (submissionFormFullControl.initHandler) {
			form.un('ready', submissionFormFullControl.initHandler);
		} else {
			submissionFormFullControl.initHandler = function(subForm) {
				submissionFormFullControl.setLoading(false);
			
				var sectionMenu = [];
				var index = 0;
				Ext.Array.each(form.getSections(), function(section) {
					sectionMenu.push({
						text: section.name,
						sectionIndex: index,
						section: section,
						handler: function() {							
							form.jumpToSection(this.sectionIndex);
							submissionFormFullControl.checkNextPrevious();
						}
					});
					index++;
				});				
				submissionFormFullControl.queryById('sectionNav').getMenu().add(sectionMenu);
				submissionFormFullControl.checkNextPrevious();
				
			};
		}		
		form.on('ready', submissionFormFullControl.initHandler);
	},
	
	load: function(submissionTemplate, entryType, userSubmission) {
		var submissionFormFullControl = this;
		
		submissionFormFullControl.setLoading(true);
		var form = submissionFormFullControl.queryById('submissionForm');
		
		submissionFormFullControl.handleInitEvent();
		form.loadTemplate(submissionTemplate, entryType, userSubmission);
		
		
	},
	
	saveSubmission: function() {
		
	},
	
	submitSumissionForApproval() {
		
	}	
	
});
