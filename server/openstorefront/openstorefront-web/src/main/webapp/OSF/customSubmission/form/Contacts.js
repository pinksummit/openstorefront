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
/* global Ext, CoreUtil, CoreService */

/* Author: cyearsley */

Ext.define('OSF.customSubmission.form.Contacts', {
	extend: 'Ext.form.Panel',
	alias: 'widget.osf-submissionform-contact',
	
	layout: 'anchor',
	bodyStyle: 'padding: 10px',
	defaults: {
		width: '100%'
	},
	
	initComponent: function () {
		this.callParent();
		
		var contactPanel = this;

		contactPanel.add(
			[
				{
					xtype: 'StandardComboBox',
					name: 'contactType',
					itemId: 'contactType',
					allowBlank: false,
					margin: '0 0 5 0',
					editable: false,
					typeAhead: false,					
					fieldLabel: 'Contact Type <span class="field-required" />',
					storeConfig: {
						url: 'api/v1/resource/lookuptypes/ContactType',
						filters: [{
								property: 'code',
								operator: '!=',
								value: /SUB/
							}]
					}
				},
				{
					xtype: 'StandardComboBox',
					name: 'organization',
					allowBlank: false,
					margin: '0 0 5 0',					
					fieldLabel: 'Organization <span class="field-required" />',
					forceSelection: false,
					valueField: 'description',
					storeConfig: {
						url: 'api/v1/resource/organizations/lookup'
					}
				},
				{
					xtype: 'StandardComboBox',
					name: 'firstName',
					allowBlank: false,
					margin: '0 0 5 0',					
					fieldLabel: 'First Name  <span class="field-required" />',
					forceSelection: false,
					valueField: 'firstName',
					displayField: 'firstName',
					maxLength: '80',
					typeAhead: false,
					autoSelect: false,
					selectOnTab: false,
					assertValue: function () {
					},
					listConfig: {
						itemTpl: [
							'{firstName} <span style="color: grey">({email})</span>'
						]
					},
					storeConfig: {
						url: 'api/v1/resource/contacts/filtered'
					},
					listeners: {
						select: function (combo, record, opts) {
							record.set('componentContactId', null);
							record.set('contactId', null);
							var contactType = combo.up('form').getComponent('contactType').getValue();
							combo.up('form').reset();
							combo.up('form').loadRecord(record);
							combo.up('form').getComponent('contactType').setValue(contactType);
						}
					}
				},
				{
					xtype: 'StandardComboBox',
					name: 'lastName',
					allowBlank: false,
					margin: '0 0 5 0',
					fieldLabel: 'Last Name <span class="field-required" />',
					forceSelection: false,
					valueField: 'lastName',
					displayField: 'lastName',
					maxLength: '80',
					typeAhead: false,
					autoSelect: false,
					selectOnTab: false,
					assertValue: function () {
					},
					listConfig: {
						itemTpl: [
							'{lastName} <span style="color: grey">({email})</span>'
						]
					},
					storeConfig: {
						url: 'api/v1/resource/contacts/filtered'
					},
					listeners: {
						select: function (combo, record, opts) {
							record.set('componentContactId', null);
							record.set('contactId', null);
							var contactType = combo.up('form').getComponent('contactType').getValue();
							combo.up('form').reset();
							combo.up('form').loadRecord(record);
							combo.up('form').getComponent('contactType').setValue(contactType);
						}
					}
				},
				{
					xtype: 'textfield',
					fieldLabel: 'Email <span class="field-required" />',
					labelWidth: 80,	
					margin: '15 0 0 0',
					maxLength: '255',
					allowBlank: false,
					regex: new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~.-]+@[a-z0-9-]+(\.[a-z0-9-]+)*", "i"),
					regexText: 'Must be a valid email address. Eg. xxx@xxx.xxx',
					name: 'email'
				},
				{
					xtype: 'textfield',
					fieldLabel: 'Phone <span class="field-required" />',
					allowBlank: false,
					labelWidth: 80,
					maxLength: '120',
					margin: '15 0 10 0',
					name: 'phone'
				},
				{
					xtype: 'SecurityComboBox'					
				},
				{
					xtype: 'DataSensitivityComboBox'
				}
			]
		);
	}
	
});
