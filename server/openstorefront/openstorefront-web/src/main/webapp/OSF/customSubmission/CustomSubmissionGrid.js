/* 
 * Copyright 2018 Space Dynamics Laboratory - Utah State University Research Foundation.
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
/* global Ext, CoreUtil */

/**
 *
 * @author cyearsley@usurf.usu.edu
 */

Ext.define('OSF.customSubmission.CustomSubmissionGrid', {
	extend: 'Ext.grid.Panel',
	form: null,
	config: {
		fieldTitle: '&nbsp',
		title: '',
		titleTip: ''
	},
	width: '90%',
	frame: true,
	border: true,
	columnLines: true,
	dockedItems: [
		{
			xtype: 'toolbar',
			itemId: 'tools',
			items: [
				{
					text: 'Add',
					iconCls: 'fa fa-lg fa-plus icon-button-color-save',
					handler: function () {
						var grid = this.up('panel');
						var win = Ext.create('Ext.window.Window', {
							title: 'New ' + grid.fieldTitle,
							width: 1000,
							height: 700,
							padding: 20,
							scrollable: true,
							items: [grid.form]
						}).show();
					}
				},
				{
					xtype: 'tbfill'
				},
				{
					text: 'Delete',
					itemId: 'removeBtn',
					disabled: true,
					iconCls: 'fa fa-lg fa-trash icon-button-color-warning',
					handler: function () {
						// delete the record from the grid...
					}
				}
			]
		}
	],

	constructor: function (config) {
		this.callParent();
		this.setTitle(config.fieldTitle + (config.titleTip ? ' <i style="margin-left: 3px;" class="fa fa-lg fa-question-circle"  data-qtip="' + config.titleTip + '"></i>' : ''));
		this.form = config.form;
		this.fieldTitle = config.fieldTitle;
		var columns = [];

		// add columns to grid
		Ext.Array.forEach(config.form.items.items, function (el, index) {
			columns.push({dataIndex: el.name, text: el.nameLabel});
		});
		this.setColumns(columns);
	}
});
