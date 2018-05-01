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

Ext.define('mobile.controller.Search', {
	extend: 'Ext.app.Controller',

	config: {
		refs: {
			landing: 'landing',
			searchResults: 'searchresults',
			details: 'details',
			topics: 'topics',
			searchField: 'osf-mobile-searchfield'
		},

		control: {
			searchField: {
				action: 'onSearch'
			},
			topics: {
				itemtap: 'onCategorySelect'
			},
			searchResults: {
				itemtap: 'onResultSelect'
			}
		}

	},
	
	onSearch: function(field, e, opts) {
		if (!this.searchResults) {
			this.searchResults = Ext.create('mobile.view.SearchResults');
		}
		this.getLanding().push(this.searchResults);
		
		this.searchResults.performSearch(field.getValue());
	},

	onCategorySelect: function (list, index, node, record, e, opts) {

		if (!this.searchResults) {
			this.searchResults = Ext.create('mobile.view.SearchResults');
		}
		
		this.getLanding().push(this.searchResults);
		
		this.searchResults.performSearch(null, record.get('componentType'));
		
	},

	onResultSelect: function (list, index, node, record, e, opts) {


		if (!this.details) {
			this.details = Ext.create('mobile.view.Details');
		}

		this.details.setRecord(record);

		this.getLanding().push(this.details);
		
		this.details.loadDetails(record.get('componentId'));
	}


});
