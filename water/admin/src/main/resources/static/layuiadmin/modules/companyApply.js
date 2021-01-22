
layui.laytpl.toDateString = function(d, format){
  var date = new Date(d || new Date())
  ,ymd = [
    this.digit(date.getFullYear(), 4)
    ,this.digit(date.getMonth() + 1)
    ,this.digit(date.getDate())
  ]
  ,hms = [
    this.digit(date.getHours())
    ,this.digit(date.getMinutes())
    ,this.digit(date.getSeconds())
  ];

  format = format || 'yyyy-MM-dd';

  return format.replace(/yyyy/g, ymd[0])
  .replace(/MM/g, ymd[1])
  .replace(/dd/g, ymd[2])
  .replace(/HH/g, hms[0])
  .replace(/mm/g, hms[1])
  .replace(/ss/g, hms[2]);
};
 
//数字前置补零
layui.laytpl.digit = function(num, length, end){
  var str = '';
  num = String(num);
  length = length || 2;
  for(var i = num.length; i < length; i++){
    str += '0';
  }
  return num < Math.pow(10, length) ? str + (num|0) : num;
};

layui.define(['table', 'form', 'element'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form
  ,element = layui.element;

  table.render({
    elem: '#LAY-app-system-order'
    ,url: '/comapply/list' //模拟接口
    ,cols: [[
    	{type: 'numbers', fixed: 'left'}
    	,{field: 'cisCompany', tminWidth: 100, title: '供水公司'}
    	,{field: 'status', title: '状态', templet: '#buttonTpl', width: 100, align: 'center'}
        ,{field: 'perName', minWidth: 100, title: '申请人'}
        ,{field: 'appdate', minWidth: 100, title: '申请日期', templet:'<div>{{ layui.laytpl.toDateString(d.appdate) }}</div>'}
        ,{field: 'address', minWidth: 100, title: '接水地址'}
        ,{field: 'applyContent', minWidth: 100, title: '申请内容'}
        ,{field: 'xzq', minWidth: 100, title: '行政区'}
        ,{field: 'contactPerson', minWidth: 100, title: '经办人'}
        ,{field: 'phone', minWidth: 100, title: '联系电话'}
    	
      ,{title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-system-order'}
    ]]
    ,page: true
    ,limit: 10
    ,limits: [10, 20, 50]
  	,text: {
	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
	}
    ,done: function(){
      element.render('progress')
    }
  });

  //监听工具条
  table.on('tool(LAY-app-system-order)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '单位接水申请'
        ,content: '/comapply/detail?incidentId='+data.incidentId
        ,area: ['95%', '95%']
        ,btn: ['办结','处理中','退单']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-app-workorder-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            
            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            table.reload('LAY-user-front-submit'); //数据刷新
            layer.close(index); //关闭弹层
          });  
          
          submit.trigger('click');
        }
      	,btn1:function (index, layero){
    		layer.alert("状态为：办结");
    	}
      	,btn2:function (index, layero){
      		layer.alert("状态为：处理中");
      	}
      	,btn3:function (index, layero){
      		layer.alert("状态为：退单");
      	}
        ,success: function(layero, index){
        	
        }
      });
    }
  });

  exports('companyApply', {})
});