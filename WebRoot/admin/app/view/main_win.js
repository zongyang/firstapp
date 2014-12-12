/*
 * File: app/view/main_win.js
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

Ext.define('MyApp.view.main_win', {
    extend: 'Ext.window.Window',

    height: 500,
    id: 'main_win',
    width: 1200,
    layout: {
        type: 'border'
    },
    closable: false,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    region: 'center',
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'panel',
                            border: false,
                            height: 600,
                            width: 800,
                            layout: {
                                type: 'border'
                            },
                            title: '用户管理模块',
                            items: [
                                {
                                    xtype: 'gridpanel',
                                    region: 'center',
                                    id: 'panel_user',
                                    margin: '0 5 0 0',
                                    titleAlign: 'center',
                                    forceFit: true,
                                    store: 'user',
                                    columns: [
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'userName',
                                            tdCls: 'grid-row',
                                            text: '用户名',
                                            flex: 1
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'sex',
                                            tdCls: 'grid-row',
                                            text: '性别',
                                            flex: 0.5
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'img',
                                            tdCls: 'grid-row',
                                            text: '图像',
                                            flex: 1.2
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'mark',
                                            tdCls: 'grid-row',
                                            text: '个性签名',
                                            flex: 1.2
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'email',
                                            tdCls: 'grid-row',
                                            text: '注册邮箱',
                                            flex: 1.2
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'regTime',
                                            tdCls: 'grid-row',
                                            text: '注册时间',
                                            flex: 1.2
                                        }
                                    ],
                                    dockedItems: [
                                        {
                                            xtype: 'toolbar',
                                            dock: 'top',
                                            items: [
                                                {
                                                    xtype: 'button',
                                                    icon: '../img/extjs_icon/delete.gif',
                                                    text: '删除',
                                                    listeners: {
                                                        click: {
                                                            fn: me.onButtonClick,
                                                            scope: me
                                                        }
                                                    }
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'pagingtoolbar',
                                            dock: 'bottom',
                                            width: 360,
                                            displayInfo: true,
                                            store: 'user'
                                        }
                                    ],
                                    selModel: Ext.create('Ext.selection.CheckboxModel', {

                                    })
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'panel',
                                    dock: 'right',
                                    border: false,
                                    layout: {
                                        align: 'stretch',
                                        type: 'vbox'
                                    },
                                    items: [
                                        {
                                            xtype: 'panel',
                                            flex: 1,
                                            border: false,
                                            width: 250,
                                            bodyStyle: {
                                                borderLeft: '1px'
                                            },
                                            title: '按日期删除聊天记录',
                                            titleAlign: 'center',
                                            items: [
                                                {
                                                    xtype: 'datefield',
                                                    id: 'date_del',
                                                    margin: '50 0 50 10',
                                                    fieldLabel: '选择日期',
                                                    labelAlign: 'right',
                                                    labelWidth: 60,
                                                    allowBlank: false,
                                                    emptyText: '选择删除的截止日期',
                                                    editable: false,
                                                    format: 'Y-m-d ',
                                                    submitFormat: 'Y-m-d'
                                                }
                                            ],
                                            dockedItems: [
                                                {
                                                    xtype: 'toolbar',
                                                    dock: 'bottom',
                                                    border: false,
                                                    items: [
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        },
                                                        {
                                                            xtype: 'button',
                                                            icon: '../img/extjs_icon/accept.gif',
                                                            text: '确定',
                                                            listeners: {
                                                                click: {
                                                                    fn: me.onButtonClick1,
                                                                    scope: me
                                                                }
                                                            }
                                                        },
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        }
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'panel',
                                            flex: 1.5,
                                            border: false,
                                            layout: {
                                                type: 'fit'
                                            },
                                            title: '发送系统通知',
                                            titleAlign: 'center',
                                            dockedItems: [
                                                {
                                                    xtype: 'toolbar',
                                                    dock: 'bottom',
                                                    border: false,
                                                    items: [
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        },
                                                        {
                                                            xtype: 'button',
                                                            height: 26,
                                                            icon: '../img/extjs_icon/accept.gif',
                                                            text: '发送'
                                                        },
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        }
                                                    ]
                                                }
                                            ],
                                            items: [
                                                {
                                                    xtype: 'textareafield',
                                                    margin: 5
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            border: false,
                            height: 600,
                            width: 800,
                            layout: {
                                type: 'border'
                            },
                            title: '管理员模块',
                            items: [
                                {
                                    xtype: 'gridpanel',
                                    region: 'center',
                                    id: 'panel_admin',
                                    margin: '0 5 0 0',
                                    titleAlign: 'center',
                                    forceFit: true,
                                    store: 'admin',
                                    columns: [
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'name',
                                            tdCls: 'grid-row',
                                            text: '用户名'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            align: 'center',
                                            dataIndex: 'auth',
                                            tdCls: 'grid-row',
                                            text: '权限'
                                        }
                                    ],
                                    dockedItems: [
                                        {
                                            xtype: 'toolbar',
                                            dock: 'top',
                                            items: [
                                                {
                                                    xtype: 'button',
                                                    icon: '../img/extjs_icon/add.gif',
                                                    text: '添加',
                                                    listeners: {
                                                        click: {
                                                            fn: me.onButtonClick3,
                                                            scope: me
                                                        }
                                                    }
                                                },
                                                {
                                                    xtype: 'button',
                                                    icon: '../img/extjs_icon/delete.gif',
                                                    text: '删除',
                                                    listeners: {
                                                        click: {
                                                            fn: me.onButtonClick2,
                                                            scope: me
                                                        }
                                                    }
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'pagingtoolbar',
                                            dock: 'bottom',
                                            width: 360,
                                            displayInfo: true,
                                            store: 'admin'
                                        }
                                    ]
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'panel',
                                    dock: 'right',
                                    border: false,
                                    layout: {
                                        align: 'stretch',
                                        type: 'vbox'
                                    },
                                    items: [
                                        {
                                            xtype: 'panel',
                                            flex: 1,
                                            border: false,
                                            width: 250,
                                            bodyStyle: {
                                                borderLeft: '1px'
                                            },
                                            title: '密码修改',
                                            titleAlign: 'center',
                                            dockedItems: [
                                                {
                                                    xtype: 'toolbar',
                                                    dock: 'bottom',
                                                    border: false,
                                                    items: [
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        },
                                                        {
                                                            xtype: 'button',
                                                            icon: '../img/extjs_icon/accept.gif',
                                                            text: '确定',
                                                            listeners: {
                                                                click: {
                                                                    fn: me.onButtonClick4,
                                                                    scope: me
                                                                }
                                                            }
                                                        },
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        }
                                                    ]
                                                }
                                            ],
                                            items: [
                                                {
                                                    xtype: 'textfield',
                                                    id: 'lb_modify_psw_old',
                                                    margin: '40 0 10 0',
                                                    fieldLabel: '原密码',
                                                    labelAlign: 'right',
                                                    labelWidth: 60,
                                                    inputType: 'password',
                                                    allowBlank: false,
                                                    blankText: '密码不能为空',
                                                    emptyText: '请输入原密码'
                                                },
                                                {
                                                    xtype: 'textfield',
                                                    id: 'lb_modify_psw_refresh',
                                                    margin: '20 0 10 0',
                                                    fieldLabel: '新密码',
                                                    labelAlign: 'right',
                                                    labelWidth: 60,
                                                    inputType: 'password',
                                                    allowBlank: false,
                                                    blankText: '密码不能为空',
                                                    emptyText: '请输入新密码'
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'panel',
                                            flex: 1,
                                            border: false,
                                            layout: {
                                                type: 'fit'
                                            },
                                            title: '发送系统通知',
                                            titleAlign: 'center',
                                            dockedItems: [
                                                {
                                                    xtype: 'toolbar',
                                                    dock: 'bottom',
                                                    border: false,
                                                    items: [
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        },
                                                        {
                                                            xtype: 'button',
                                                            height: 26,
                                                            icon: '../img/extjs_icon/accept.gif',
                                                            text: '发送'
                                                        },
                                                        {
                                                            xtype: 'tbspacer',
                                                            flex: 1
                                                        }
                                                    ]
                                                }
                                            ],
                                            items: [
                                                {
                                                    xtype: 'textareafield',
                                                    margin: 5
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    onButtonClick: function(button, e, eOpts) {
        var panel = Ext.getCmp('panel_user');
        var selections = panel.selModel.getSelection();
        var userNames = [];

        if (selections.length <= 0) {
            Ext.Msg.alert('提示', '请选择需要删除的用户！');
            return;
        }

        for (var i = 0; i < selections.length; i++) {
            userNames.push(selections[i].get('userName'));
        }

        Ext.MessageBox.show({
            title: '删除确认',
            msg: '是否要删除 <font class="blue-color">' + userNames.join(',') + '</font> 的所有相关信息？',
            buttons: Ext.MessageBox.YESNO,
            fn: function(e) {
                if (e !== 'yes') {
                    return;
                }
                Ext.Ajax.request({
                    url: '../admin',
                    params: {
                        method: 'del_user',
                        userNames: userNames.join(',')

                    },
                    success: function(response) {
                        var obj = Ext.JSON.decode(response.responseText);
                        if (obj.success === false) {
                            Ext.Msg.alert('提示', obj.msg);
                        }

                        if (obj.success === true) { 
                            Ext.Msg.alert('提示', obj.msg);
                            panel.getStore().load();
                        }
                    }
                });
            }
        });
    },

    onButtonClick1: function(button, e, eOpts) {
        var time = Ext.getCmp('date_del').getRawValue();


        Ext.MessageBox.show({
            title: '聊天删除确认',
            msg: '是否要删除截止到 <font class="blue-color">' + time + '</font> 的所有聊天记录？',
            buttons: Ext.MessageBox.YESNO,
            fn: function(e) {
                if (e !== 'yes') {
                    return;
                }
                Ext.Ajax.request({
                    url: '../admin',
                    params: {
                        method: 'del_chat',
                        strDate: time

                    },
                    success: function(response) {
                        var obj = Ext.JSON.decode(response.responseText);
                        if (obj.success === false) {
                            Ext.Msg.alert('提示', obj.msg);
                        }

                        if (obj.success === true) {
                            Ext.Msg.alert('提示', obj.msg);
                        }
                    }
                });
            }
        });
    },

    onButtonClick3: function(button, e, eOpts) {
        var win = Ext.getCmp('add_admin_win');
        if (!win) {
            win = Ext.create('MyApp.view.add_admin_win');
        }
        win.show();
    },

    onButtonClick2: function(button, e, eOpts) {
        var panel = Ext.getCmp('panel_admin');
        var selections = panel.selModel.getSelection();
        var admins = [];

        if (selections.length <= 0) {
            Ext.Msg.alert('提示', '请选择需要删除的管理员！');
            return;
        }

        for (var i = 0; i < selections.length; i++) {
            admins.push(selections[i].get('name'));
        }

        Ext.MessageBox.show({
            title: '删除确认',
            msg: '是否要删除管理员 <font class="blue-color">' + admins.join(',') + '</font> ？',
            buttons: Ext.MessageBox.YESNO,
            fn: function(e) {
                if (e !== 'yes') {
                    return;
                }
                Ext.Ajax.request({
                    url: '../admin',
                    params: {
                        method: 'del_admin',
                        name: admins.join(',')

                    },
                    success: function(response) {
                        var obj = Ext.JSON.decode(response.responseText);
                        if (obj.success === false) {
                            Ext.Msg.alert('提示', obj.msg);
                        }

                        if (obj.success === true) { 
                            Ext.Msg.alert('提示', obj.msg);
                            panel.getStore().load();
                        }
                    }
                });
            }
        });
    },

    onButtonClick4: function(button, e, eOpts) {
        var old = Ext.getCmp('lb_modify_psw_old');
        var refresh = Ext.getCmp('lb_modify_psw_refresh');
        var panel = Ext.getCmp('panel_admin');
        var model = panel.selModel.getLastSelected();

        if (!old.isValid() || !refresh.isValid()) {
            return;
        }

        if (!model) {
            Ext.Msg.alert('提示', '请选择需要修改密码的管理员！');
            return;
        }

        Ext.Ajax.request({
            url: '../admin',
            params: {
                method: 'password_modify',
                name: model.get('name'),
                old: old.getValue(),
                refresh: refresh.getValue()
            },
            success: function(response) {
                var obj = Ext.JSON.decode(response.responseText);
                if (obj.success === false) {
                    Ext.Msg.alert('提示', obj.msg);
                }

                if (obj.success === true) {
                    Ext.Msg.alert('提示', obj.msg);
                }
            }
        });
    }

});