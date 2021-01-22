
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
        ,url: '/home/multi/list' //模拟接口
        ,cols: [[
            {type: 'numbers', fixed: 'left'}
            ,{field: 'companyName', minWidth: 100, title: '供水公司'}
            ,{field: 'userNo', minWidth: 100, title: '户号'}
            ,{field: 'statusDict', title: '状态', templet: '#buttonTpl', width: 100, align: 'center'}
            ,{field: 'applicant', minWidth: 100, title: '申请人'}
            ,{field: 'appdateString', width: 120, title: '申请日期'}
            ,{field: 'expireString', width: 120, title: '过期时间'}
            ,{field: 'shwAddress', width: 210, title: '发生地址'}
            ,{field: 'phone', minWidth: 100, title: '手机'}
            
            ,{field: 'ywtbSuccess', minWidth: 100, title: '一网通办是否收件',templet: function(d){
          	  if(d.ywtbSuccess == true){
          		  return '收件';
          	  }else{
          		  return '未收件';
          	  }
            }}
            ,{field: 'ywxtSuccess', minWidth: 100, title: '业务系统发送状态',templet: function(d){
            	  if(d.ywxtSuccess == true){
              		  return '发送成功';
              	  }else{
              		  return '发送失败';
              	  }
                }}
            ,{field: 'updateTime', minWidth: 100, title: '审核时间',templet: function(d){
          	  if(d.ifResult == '01'){
          		  return d.updateTime;
          	  }else if(d.ifResult == '02'){
          		  return d.updateTime;
          	  }else{
          		return '未审核';
          	  }
            }}
            ,{field: 'ifResult', minWidth: 100, title: '业务审核状态',templet: function(d){
            	  if(d.ifResult == '01'){
              		  return '审核通过';
              	  }else if(d.ifResult == '02'){
              		  return '审核不通过';
              	  }else{
              		return '未审核';
              	  }
                }}
            
            ,{field: 'mail', minWidth: 100, title: '电子邮箱'}
            ,{field: 'peopleNum', minWidth: 100, title: '收益人数'}
            ,{field: 'reqType', minWidth: 100, title: '办理类型',templet: function(d){
            	  if(d.reqType == '01'){
              		  return '新办';
              	  }else if(d.reqType == '02'){
              		  return '续办';
              	  }else{
              		  return '终止';
              	  }
                }}
            ,{field: 'applyAddress', minWidth: 100, title: '申请地址'}
            ,{field: 'addressCheck', minWidth: 100, title: '地址校核结果',templet: function(d){
          	  if(d.addressCheck == '01'){
          		  return '地址校验一致';
          	  }else{
          		  return '地址校验不一致';
          	  }
            }}
            ,{field: 'operType', minWidth: 100, title: '办件类型',templet: function(d){
            	  if(d.operType == '01'){
              		  return '即办件';
              	  }else{
              		  return '流转件';
              	  }
                }}
            ,{field: 'settleMethod', minWidth: 100, title: '优惠方式',templet: function(d){
          	  if(d.settleMethod == '01'){
          		  return '每户年度增加100立方米水量';
          	  }else{
          		  return '3.65元/立方米综合水价';
          	  }
            }}
            ,{field:'deal', title:'是否处理', width:110, templet: '#test-table-checkboxTpl', unresize: true}
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
                ,title: '一户多人口'
                ,content: '/home/multi/detail?incidentId='+data.incidentId
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

    exports('multi', {})
});