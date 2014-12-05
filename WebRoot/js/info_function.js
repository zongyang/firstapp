$(function() {
	// 创建store的model
	create_area_model();
	// 默认打开的是个人资料tab
	set_main_info();
	// 加载省份的store
	load_store('province', '');

	$('.set-banner-ul li').click(set_banner_ul_li_click);
});

function set_banner_ul_li_click(event) {
	$(event.currentTarget).addClass('active').siblings().removeClass('active');
}

function set_main_info() {
	var panel = Ext.create('Ext.panel.Panel', {
		border : false,
		margin : '50',
		layout : {
			align : 'stretch',
			type : 'vbox'
		},
		renderTo : $('.set .info')[0],
		items : [ {
			xtype : 'textfield',
			margin : 20,
			maxWidth : 400,
			fieldLabel : '用户名',
			labelAlign : 'right',
			disabled : true,
		}, {
			xtype : 'fieldcontainer',
			margin : 20,
			width : 600,
			layout : {
				align : 'stretch',
				type : 'hbox'
			},
			items : [ {
				xtype : 'combobox',
				margin : '0 20 0 0',
				fieldLabel : '地 区',
				labelAlign : 'right',
				id:'com_province',
				emptyText : '选择省份',
				editable : false,
				store : create_area_store('province'),
				displayField : 'name',
				valueField : 'id',
				listeners : {
					select : function(combo, records, eOpts) {
						Ext.getCmp('com_city').clearValue();
						Ext.getCmp('com_county').clearValue();
						load_store('city', records[0].get('id'));
					}
				}
			}, {
				xtype : 'combobox',
				margin : '0 20 0 0',
				labelAlign : 'right',
				id:'com_city',
				emptyText : '选择城市',
				editable : false,
				store : create_area_store('city'),
				displayField : 'name',
				valueField : 'id',
				listeners : {
					select : function(combo, records, eOpts) {
						Ext.getCmp('com_county').clearValue();
						load_store('county', records[0].get('id'));
					}
				}
			}, {
				xtype : 'combobox',
				labelAlign : 'right',
				id:'com_county',
				emptyText : '选择区县',
				editable : false,
				store : create_area_store('county'),
				displayField : 'name',
				valueField : 'id'
			} ]
		}, {
			xtype : 'radiogroup',
			margin : 20,
			maxWidth : 300,
			fieldLabel : '性 别',
			labelAlign : 'right',
			items : [ {
				xtype : 'radiofield',
				name : 'sex',
				checked:true,
				boxLabel : '保密'
			}, {
				xtype : 'radiofield',
				name : 'sex',
				boxLabel : '男'
			}, {
				xtype : 'radiofield',
				name : 'sex',
				boxLabel : '女'
			} ]
		}, {
			xtype : 'textareafield',
			margin : 20,
			maxWidth : 400,
			fieldLabel : '个性签名',
			labelAlign : 'right'
		} ]
	});

	return panel;
}

function create_area_model() {
	var model = Ext.define('Area', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'id'
		}, {
			name : 'name'
		}, {
			name : 'parent'
		} ]
	});
	return model;
}
function create_area_store(store_id) {
	var store = Ext.create('Ext.data.Store', {
		model : 'Area',
		storeId : store_id,
		proxy : {
			type : 'ajax',
			url : '',
			extraParams : {
				areaId : '',
				method : 'area_req'
			},
			reader : {
				type : 'json'
			}
		}
	});
	return store;
}
function load_store(store_id, areaId, callback) {
	var store = Ext.StoreMgr.get(store_id);
	var proxy = store.getProxy();
	var extraParams = proxy.extraParams;

	proxy.url = 'action';

	if (extraParams.areaId == '' || extraParams.areaId == areaId) {
		extraParams.areaId=areaId;
		return ;
	}
	
	extraParams.areaId=areaId;
	store.load({
		scope : this,
		callback : function(records, operation, success) {
			// the operation object
			// contains all of the details of the load operation
			if (callback) {
				callback();
			}
		}
	});
}
