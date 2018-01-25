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

Ext.define('OSF.customSubmission.CustomSubmissionField', {
	extend: 'Ext.Mixin',
	alias: 'osf.customSubmissionField.CSF',

	config: {
		labelTip: '',		// tool tip (?)
		labelCode: '',		// A code to be displayed prior to question
		label: '',
		fieldMapping: '',	// relative to an entry, what will this question be mapped to?
		labelAlign: 'top',
		labelWidth: '33%',
		codeMargin: 15,
		tipMarign: 5,
		fieldType: '',
		validationDataModel: {
			validationType: '',
			validationMessage: ''
		}
	},
	width: '75%',

	setCSFLabelCode: function (newCode) {
		this.setLabelCode(newCode);
		this._resetFieldLabel();
	},

	setCSFLabelTip: function (newTip) {
		this.setLabelTip(newTip);
		this._resetFieldLabel();	
	},

	setCSFLabel: function (newString) {
		this.setLabel(newString);
		this._resetFieldLabel();
	},

	getValue: function () {
		console.warn('No value handling has been made the the field type: ', this.fieldType);
	},

	handleChange: function () {
		console.warn('No change handling has been made the the field type: ', this.fieldType);
	},

	getCommentField: function () {
		return this.customSubmissionSection ? this.customSubmissionSection.commentField : undefined;
	},

	getScopeField: function () {
		return this.customSubmissionSection ? this.customSubmissionSection.scopeField : undefined;
	},

	initField: function (fieldComponent) {

		this.setCSFLabel.call(fieldComponent, fieldComponent.getLabel());
	},

	// =============================================
	//
	// P R I V A T E   M E T H O D S
	//
	// =============================================

	// resets the field label for the field
	_resetFieldLabel: function () {
		var labelMetaData = {
			tip: '',
			code: ''
		}
		if (this.getLabelTip()) {
			labelMetaData.tip = '<i style="margin-left: ' + this.getTipMarign() + 'px;" class="fa fa-lg fa-question-circle"  data-qtip="' + this.getLabelTip() + '"></i>';
		}
		if (this.getLabelCode()) {
			labelMetaData.code = this.getLabelCode() + '.<span style="margin-right:' + this.getCodeMargin() + 'px;"></span>';
		}		

		this.setFieldLabel(labelMetaData.code + this.getLabel() + labelMetaData.tip);
	}
});
