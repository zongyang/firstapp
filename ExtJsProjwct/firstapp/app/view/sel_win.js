/*
 * File: app/view/sel_win.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MyApp.view.sel_win', {
    extend: 'Ext.window.Window',

    height: 350,
    id: 'sel_win',
    width: 400,
    layout: {
        align: 'stretch',
        type: 'vbox'
    },
    closable: false,
    title: '登录模块选择',
    titleAlign: 'center',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'button',
                    margin: '50 50 0 50',
                    scale: 'large',
                    text: '进入聊天模块',
                    listeners: {
                        click: {
                            fn: me.onButtonClick,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'button',
                    margin: '50 50 0 50',
                    scale: 'large',
                    text: '进入管理员模块',
                    listeners: {
                        click: {
                            fn: me.onButtonClick1,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'button',
                    margin: '50 50 0 50',
                    scale: 'large',
                    text: '进入管理员模块',
                    listeners: {
                        click: {
                            fn: me.onButtonClick11,
                            scope: me
                        }
                    }
                }
            ]
        });

        me.callParent(arguments);
    },

    onButtonClick: function(button, e, eOpts) {
        location.href='../login.html';
    },

    onButtonClick1: function(button, e, eOpts) {
        Ext.getCmp('sel_win').close();
        Ext.create('MyApp.view.login_win').show();

    },

    onButtonClick11: function(button, e, eOpts) {
        Ext.getCmp('sel_win').close();
        Ext.create('MyApp.view.ip_win').show();


    }

});