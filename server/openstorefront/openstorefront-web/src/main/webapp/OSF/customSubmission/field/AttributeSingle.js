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


/* global Ext, fieldPanel */

Ext.define('OSF.customSubmission.field.AttributeSingle', {
	extend: 'Ext.panel.Panel',	
	
	width: '100%',
	layout: 'anchor',
	
	initComponent: function () {
		var panel = this;
		panel.callParent();
			
		fieldPanel.label = Ext.create('Ext.panel.Panel', {
			layout: 'hbox',
			items: [
				{
					xtype: 'panel',
					flex: 1,
					html: fieldPanel.createQuestionLabel()
				},
				{
					xtype: 'checkbox',
					boxLabel: 'Private'
				}
			]
		});	
		
		//use filetype to determine rendering
		
		
		panel.add([
			fieldPanel.label
		]);	
	}

});