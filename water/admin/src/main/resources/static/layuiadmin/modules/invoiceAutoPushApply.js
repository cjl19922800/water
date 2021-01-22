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
    ,url: '/home/pushInvoice/list?incidentType=McsItemType.ElecInvoiceAutoPush' //模拟接口11
    ,cols: [[
      {type: 'numbers', fixed: 'left'}
      ,{field: 'companyName', tminWidth: 100, title: '供水公司'}
      ,{field: 'statusDict', title: '状态', templet: '#buttonTpl', width: 100, align: 'center'}
      ,{field: 'applicant', minWidth: 100, title: '申请人'}
      ,{field: 'appdateString', minWidth: 100, title: '申请日期'}
      ,{field: 'expireString', width: 120, title: '过期时间'}
      ,{field: 'shwAddress', minWidth: 100, title: '用水地址'}
//      ,{field: 'xzqName', minWidth: 100, title: '行政区'}
//      ,{field: 'townName', minWidth: 100, title: '街道'}
//      ,{field: 'applyContentDict', minWidth: 100, title: '接水类别'}
//      ,{field: 'conName', minWidth: 100, title: '联系人'}
      ,{field: 'contactValue', minWidth: 100, title: '联系电话'}
      ,{field: 'applyType', minWidth: 100, title: '申请类型',templet: function(d){
          	  if(d.applyType == '1'){
          		  return '申请';
          	  }else if(d.applyType == '2'){
          		  return '修改';
          	  }else{
          		return '取消';
          	  }
            }}
      ,{field:'deal', title:'是否处理', width:110, templet: '#test-table-checkboxTpl', unresize: true}
     // ,{title: '转单操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-system-transfer'}
      ,{title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-system-order'}
    ]]
    ,page: true
    ,limit: 10
    ,limits: [10, 20, 50]
    ,text: {
	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
	}
  ,done: function (res, curr, count) {
      element.render('progress')
      
      var that = this.elem.next();
      res.data.forEach(function (item, index) {
    	  var tr = that.find(".layui-table-box tbody tr[data-index='" + index + "']");
    	  if (item.expireStatus === 1 ) {
              tr.css("background-color", "#FF5722");
              tr.css("color", "white");
            }else if(item.expireStatus === 2){
            	tr.css("background-color", "#FFFF00");
            }else{
            	
            }
        });
      
    }
  });

 //监听工具条
    table.on('tool(LAY-app-system-order)', function(obj){
        var data = obj.data;
        if(obj.event === 'edit'){
            var tr = $(obj.tr);
            layer.open({
                type: 2
                ,title: '居民电子发票自动推送'
                ,content: '/home/pushInvoice/detail?incidentId='+data.incidentId
                ,area: ['100%', '100%']
            	,btn: ['确定', '取消']
                ,yes: function(index, layero){
                	layer.close(index);
                }
                ,success: function(layero, index){
                }
            });
        }
    });

  exports('invoiceAutoPushApply', {})
});	