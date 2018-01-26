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

Ext.define('OSF.customSubmission.CustomSubmissionSection', {
	extend: 'Ext.form.Panel',
	layout: 'column',
	bodyPadding: 10,
	items: [{columnWidth: 0.75, items: []},{columnWidth: 0.25, items: []}],
	config: {
		isScoped: false,
		canComment: false,
		scopeLabel: '&nbsp',
		fieldComment: '',
		commentLabel: 'If yes, state why',
		nameLabel: null
	},
	customSubmissionField: null,
	scopeField: null,
	commentField: null,

	constructor: function (config) {

		this.callParent();
		if (typeof config.fieldType !== 'undefined') {

			this.setIsScoped(config.isScoped ? config.isScoped : this.getIsScoped());
			this.setScopeLabel(config.scopeLabel ? config.scopeLabel : this.getScopeLabel());
			this.setFieldComment(config.fieldComment ? config.fieldComment : this.getFieldComment());
			this.setCommentLabel(config.commentLabel ? config.commentLabel : this.getCommentLabel());
			this.setCanComment(config.canComment ? config.canComment : this.getCanComment());
			this.name = config.name;
			this.nameLabel = this.nameLabel ? this.nameLabel : this.name.replace(/(^| )(\w)/g, function(x) {return x.toUpperCase();});
			config.customSubmissionSection = this;

			// define scopeField
			this.scopeField = Ext.create('Ext.form.ComboBox', {
				labelAlign: 'top',
				fieldLabel: this.getScopeLabel(),
				valueField: 'value',
				displayField: 'label',
				store: Ext.create('Ext.data.Store', { field: ['value', 'label'],
					data: [{value: 'public', label: 'Public'}, {value: 'private', label: 'Private',}]
				}),
				allowBlank: false,
				editable: false,
				value: 'public',
				name: 'scope',
				labelSeparator: ''
			});

			// define commentField
			this.commentField = Ext.create('Ext.form.field.TextArea', {
				fieldLabel: this.getCommentLabel(),
				disabled: true,
				labelAlign: 'top',
				width: '50%'
			});

			try {
				this.customSubmissionField = Ext.create('OSF.customSubmission.field.' + config.fieldType, config);
				this._placeFields();
			}
			catch(error) {
				console.error('When trying to create a custom submission field, the error was produced: ', error);
			}
		}
		else {
			console.error('ERROR: cannot create CustomSubmissionSection without a fieldType.');
		}


	},

	getValue: function () {
		return this.customSubmissionField.getValue();
	},

	// =============================================
	//
	// P R I V A T E   M E T H O D S
	//
	// =============================================

	// Places the Custom Submission Field and Comment field in the first column, and the CSF scope in the second column (if specified.)
	_placeFields: function () {

		var customField = this.customSubmissionField;
		var col1 = this.items.items[0];
		var col2 = this.items.items[1];
		col1.add(customField);

		if (this.getFieldComment()) {
			col1.add(Ext.create('Ext.form.Label', {text: this.getFieldComment(), style: 'margin-top: 1px; color: #aaa'}));
		}

		if (this.getCanComment()) {
			col1.add(this.commentField);
		}

		if (this.getIsScoped()) {
			col2.add(this.scopeField);
		}
	}
});
