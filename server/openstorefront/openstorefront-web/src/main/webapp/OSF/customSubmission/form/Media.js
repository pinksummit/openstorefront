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

Ext.define('OSF.customSubmission.form.Media', {
	extend: 'Ext.form.Panel',
	
	layout: 'anchor',
	bodyStyle: 'padding: 10px',	
	
	initComponent: function () {
		this.callParent();
		
		var mediaPanel = this;

		mediaPanel.add([
			{
				xtype: 'StandardComboBox',
				name: 'mediaTypeCode',
				colName: 'mediaType',
				allowBlank: false,
				margin: '0 0 15 0',
				editable: false,
				typeAhead: false,
				width: 450,
				fieldLabel: 'Media Type: <span class="field-required" />',
				labelAlign: 'left',
				storeConfig: {
					url: 'api/v1/resource/lookuptypes/MediaType'
				}
			},
			{
				xtype: 'textfield',
				fieldLabel: 'Caption <span class="field-required" />',
				allowBlank: false,
				maxLength: '255',
				width: 450,
				name: 'caption'
			},
			{
				xtype: 'checkbox',
				boxLabel: '<strong>Hide In Carousel</strong>',
				width: 450,
				name: 'hideInDisplay',
				colName: 'hideInCarousel'
			},
			{
				xtype: 'checkbox',
				boxLabel: '<strong>Used Inline</strong>',
				width: 450,
				name: 'usedInline'
			},
			{
				xtype: 'checkbox',
				width: 450,
				boxLabel: '<strong>Icon</strong> <i class="fa fa-question-circle"  data-qtip="Designates a media item to be used as an icon. There should only be one active on a entry at a time."></i>',
				name: 'iconFlag',
				colName: 'showIcon'
			},
			{
				xtype: 'button',
				text: 'Local Resource',
				width: 450,
				margin: '0 0 15 0',
				menu: [
					{
						text: 'Local Resource',
						handler: function () {
							var form = this.up('form');
							var button = this.up('button');
							button.setText('Local Resource');
							form.getForm().findField('file').setHidden(false);
							form.getForm().findField('originalLink').setHidden(true);
							
							form.query('[name="iconFlag"]')[0].setDisabled(false);
						}
					},
					{
						text: 'External Link',
						handler: function () {
							var form = this.up('form');
							var button = this.up('button');
							button.setText('External Link');
							form.getForm().findField('file').setHidden(true);
							form.getForm().findField('originalLink').setHidden(false);
							
							form.query('[name="iconFlag"]')[0].setDisabled(true);
						}
					}
				]
			},
			{
				xtype: 'fileFieldMaxLabel',
				itemId: 'upload',
				name: 'file',
				colName: 'filePath',
				width: 450,
				resourceLabel: 'Upload Media'
			},
			{
				xtype: 'textfield',
				fieldLabel: 'Link',
				hidden: true,
				width: 450,
				maxLength: '255',
				emptyText: 'http://www.example.com/image.png',
				name: 'originalLink',
				colName: 'externalLink'
			},
			{
				xtype: 'SecurityComboBox',
				itemId: 'securityMarkings'
			},
			{
				xtype: 'DataSensitivityComboBox',
				width: 450,
				labelAlign: 'left'				
			}
		]);
	}
});
