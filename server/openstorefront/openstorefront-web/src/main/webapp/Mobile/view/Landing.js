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

Ext.define('mobile.view.Landing', {
	extend: 'Ext.navigation.View',
	xtype: 'landing',
	requires: [
		'mobile.view.Topics',
		'mobile.view.SearchField'
	],
	
	fullscreen: true,
	//autoDestroy: false,
	
	navigationBar: {
		cls: 'nav-back-color',
		items: [
			{
				xtype: 'button',
				iconCls: 'fa fa-lg fa-bars mobile-nav',
				style: 'background: transparent; color: white; border: 0px solid; ',
				align: 'right',
				handler: function() {
					Ext.Viewport.toggleMenu('right');
				}
			}
		]
	},	
	items: [
		{
			title: '<span class="mobile-nav">SPOON</span>',	
			scrollable: true,
			items: [
				{
					xtype: 'panel',
					height: 125,					
					docked: 'top',
					items: [
						{
							xtype: 'image',
							centered: true,
							src: 'images/di2elogo-lg.png',
							width: 375,
							height: 125
						}
					]
				},
				{
					xtype: 'osf-mobile-searchfield',
					docked: 'top'					
				},
				{
					xtype: 'topics'
				}
			]
		}
	]	
	


});

