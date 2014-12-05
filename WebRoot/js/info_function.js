$(function() {
	// 创建store的model
	create_area_model();
	// 默认打开的是个人资料tab
	set_main_info();
	// 加载省份的store
	load_store('province', '');
	// 右侧的tab切换
	$('.set-banner-ul li').click(set_banner_ul_li_click);
	// 信息修改
	$('.set-main-info-btn').click(set_main_info_btn_click);
	
	//个人资料初始化
	init_info();
});
function init_info() {
	var mark = Ext.getCmp('info_mark');
	var area = Ext.getCmp('info_area');
	$.ajax({
		url : 'action',
		data : {
			method : 'get_userInfo_req',
			userName : g_user.getUserName()
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);

			radio_group_select(obj.sex);
			mark.setValue(obj.mark);
			area.setValue(obj.area);
		}
	});
}
function info_area_change() {
	var area = Ext.getCmp('info_area');
	var province = Ext.getCmp('info_province').getRawValue();
	var city = Ext.getCmp('info_city').getRawValue();
	var county = Ext.getCmp('info_county').getRawValue();
	var val = '';

	if (county == '' && city == '' && province != '') {
		val = province;
	} else if (county == '' && city != '' && province != '') {
		val = province + '-' + city;
	} else if (county != '' && city != '' && province != '') {
		val = province + '-' + city + '-' + county;
	}
	area.setValue(val);
}
function radio_group_select(name) {
	var items = Ext.getCmp('info_sex').items.items;
	for (var i = 0; i < items.length; i++) {
		if (items[i].boxLabel == name) {
			items[i].setValue(true);
			break;
		}
	}
}
// tab切换事件
function set_banner_ul_li_click(event) {
	$(event.currentTarget).addClass('active').siblings().removeClass('active');
}
// 创建消息窗口
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
			xtype : 'combobox',
			margin : 20,
			maxWidth : 400,
			id : 'info_userName',
			fieldLabel : '用户名',
			labelAlign : 'right',
			value : g_user.getUserName(),
			editable : false,
			hideTrigger : true
		}, {
			xtype : 'combobox',
			labelAlign : 'right',
			id : 'info_area',
			fieldLabel : '地区',
			maxWidth : 400,
			margin : 20,
			editable : false,
			hideTrigger : true
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
				fieldLabel : '选择地区',
				id : 'info_province',
				labelAlign : 'right',
				emptyText : '选择省份',
				editable : false,
				store : create_area_store('province'),
				displayField : 'name',
				valueField : 'id',
				listeners : {
					select : function(combo, records, eOpts) {
						Ext.getCmp('info_city').clearValue();
						Ext.getCmp('info_county').clearValue();

						load_store('city', records[0].get('id'));
						info_area_change();
					}
				}
			}, {
				xtype : 'combobox',
				margin : '0 20 0 0',
				labelAlign : 'right',
				id : 'info_city',
				emptyText : '选择城市',
				editable : false,
				store : create_area_store('city'),
				displayField : 'name',
				valueField : 'id',
				listeners : {
					select : function(combo, records, eOpts) {
						Ext.getCmp('info_county').clearValue();
						load_store('county', records[0].get('id'));
						info_area_change();
					}
				}
			}, {
				xtype : 'combobox',
				labelAlign : 'right',
				id : 'info_county',
				emptyText : '选择区县',
				editable : false,
				store : create_area_store('county'),
				displayField : 'name',
				valueField : 'id',
				listeners : {
					select : function(combo, records, eOpts) {
						info_area_change();
					}
				}
			} ]
		}, {
			xtype : 'radiogroup',
			margin : 20,
			maxWidth : 300,
			fieldLabel : '性 别',
			id : 'info_sex',
			labelAlign : 'right',
			items : [ {
				xtype : 'radiofield',
				name : 'sex',
				checked : true,
				inputValue : '保密',
				boxLabel : '保密'
			}, {
				xtype : 'radiofield',
				name : 'sex',
				inputValue : '男',
				boxLabel : '男'
			}, {
				xtype : 'radiofield',
				name : 'sex',
				inputValue : '女',
				boxLabel : '女'
			} ]
		}, {
			xtype : 'textareafield',
			margin : 20,
			maxWidth : 400,
			id : 'info_mark',
			fieldLabel : '个性签名',
			labelAlign : 'right'
		} ]
	});

	return panel;
}
// 消息修改确认
function set_main_info_btn_click() {
	var userName = g_user.getUserName();
	var province = Ext.getCmp('info_province').getValue();
	var city = Ext.getCmp('info_city').getValue();
	var county = Ext.getCmp('info_county').getValue();
	var sex = Ext.getCmp('info_sex').getValue().sex;
	var mark = Ext.getCmp('info_mark').getValue();

	var area = '';
	if (county == null && city == null) {
		area = province;
	} else if (county == null && city != null) {
		area = city;
	} else if (county != null && city != null && province != null) {
		area = county;
	} else {
		Ext.Msg.alert('提示', '请选择正确的地区');
		return;
	}

	var obj = {
		userName : userName,
		area : (area == null) ? '' : area,
		sex : (sex == null) ? '' : sex,
		mark : (mark == null) ? '' : mark,
	}

	$.ajax({
		url : 'action',
		data : {
			method : 'info_modify_req',
			model : Ext.JSON.encode(obj)
		},
		success : function(data) {
			var obj = Ext.JSON.decode(data);
			if (!obj.success) {// 修改失败
				Ext.Msg.alert('提示', obj.msg);
				return;
			}
			Ext.Msg.alert('提示', obj.msg);// 修改成功
		}
	});

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
		extraParams.areaId = areaId;
		return;
	}

	extraParams.areaId = areaId;
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
