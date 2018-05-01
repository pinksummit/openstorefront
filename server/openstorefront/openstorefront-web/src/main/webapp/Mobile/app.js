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

Ext.application({
	name: 'mobile',
	appFolder: 'Mobile',
		
	views: ['Landing', 'SearchResults', 'Details'],
	controllers: ['Search'],
	launch: function () {
		var menu = {
            //layout : 'fit',
            width  : '75%',
            items  : [
				{
					text: 'User Profile',
					iconCls: 'fa fa-lg fa-user'
				},
				{
					text: 'Logout'
				}
				/*
                {
                    xtype : 'treelist',
                    store : new Ext.data.TreeStore({
                        root: {
                            expanded: true,
                            children: [{
                                text: 'Home',
                                iconCls: 'x-fa fa-home',
                                children: [{
                                    text: 'Messages',
                                    iconCls: 'x-fa fa-inbox',
                                    leaf: true
                                },{
                                    text: 'Archive',
                                    iconCls: 'x-fa fa-database',
                                    children: [{
                                        text: 'First',
                                        iconCls: 'x-fa fa-sliders',
                                        leaf: true
                                    },{
                                        text: 'No Icon',
                                        iconCls: null,
                                        leaf: true
                                    }]
                                },{
                                    text: 'Music',
                                    iconCls: 'x-fa fa-music',
                                    leaf: true
                                },{
                                    text: 'Video',
                                    iconCls: 'x-fa fa-film',
                                    leaf: true
                                }]
                            },{
                                text: 'Users',
                                iconCls: 'x-fa fa-user',
                                children: [{
                                    text: 'Tagged',
                                    iconCls: 'x-fa fa-tag',
                                    leaf: true
                                },{
                                    text: 'Inactive',
                                    iconCls: 'x-fa fa-trash',
                                    leaf: true
                                }]
                            },{
                                text: 'Groups',
                                iconCls: 'x-fa fa-group',
                                leaf: true
                            },{
                                text: 'Settings',
                                iconCls: 'x-fa fa-wrench',
                                children: [{
                                    text: 'Sharing',
                                    iconCls: 'x-fa fa-share-alt',
                                    leaf: true
                                },{
                                    text: 'Notifications',
                                    iconCls: 'x-fa fa-flag',
                                    leaf: true
                                },{
                                    text: 'Network',
                                    iconCls: 'x-fa fa-signal',
                                    leaf: true
                                }]
                            }]
                        }
                    })
                }
				*/
            ]
        };

        Ext.Viewport.setMenu(menu, {
            side   : 'right',
            reveal : false
        });

		
		Ext.fly('appLoadingIndicator').destroy();
		Ext.Viewport.add(Ext.create('mobile.view.Landing'));
		
	}
});