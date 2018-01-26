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

Ext.define('OSF.customSubmission.field.ContactsGrid', {
	// extend: 'Ext.form.field.Text',
	extend: 'Ext.form.FieldContainer',
	isTextInput: false,
	mixins: {
		root: 'OSF.customSubmission.CustomSubmissionField'
	},

	initComponent: function () {
		this.callParent();
		this.mixins.root.initField(this);
	},

	getValue: function () {
		return this.items.items[0].getValue();
	},
	allowBlank: false,

	layout: 'hbox',
	defaultType: 'radiofield',
	items: [
		{
			boxLabel: 'Yes',
			name: 'question',
			inputValue: true,
			listeners: {
				change: function () {

					if (this.checked) {
						this.up('fieldcontainer').getCommentField().setDisabled(false);
					}
				}
			}
		},
		{
			boxLabel: 'No',
			name: 'question',
			inputValue: false,
			listeners: {
				change: function (val) {
					if (this.checked) {
						this.up('fieldcontainer').getCommentField().setDisabled(true);
					}
				}
			}
		}
	]
});
