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

Ext.define('mobile.view.Details', {
	extend: 'Ext.Panel',
	xtype: 'details',
	

	config: {
		scrollable:true,
		text: '<span class="mobile-nav">Details</span>',
		tpl: new Ext.XTemplate(
			'<div style="padding-top: 5px; padding-bottom: 5px; font-size: 24px;">{name}</div>',
			'<b>{componentTypeDecription}</b><br>',
			'{description}'						
		)
	},
	loadDetails : function(componentId) {
		var detailsPanel = this;
					
		detailsPanel.setMasked({
			xtype: 'loadmask',
			message: 'Loading Details...'
		});
		
		Ext.Ajax.request({
			url: 'api/v1/resource/components/' + componentId + '/detail',
			callback: function() {
				detailsPanel.setMasked(false);
			},
			success: function(response, opts) {
				var data = Ext.decode(response.responseText);
				detailsPanel.setData(data);
			}
		});
	}


});
