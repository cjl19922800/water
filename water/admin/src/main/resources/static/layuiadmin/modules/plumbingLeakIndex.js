
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
  
 console.log(element)

  table.render({
    elem: '#LAY-app-system-order'
    ,url: '/home/repairs/list?itemTypeTemp=' + document.getElementById("itemType").value //模拟接口
    ,title: "报修"
    ,toolbar: '#test-table-toolbar-toolbarDemo'
	,defaultToolbar: ['filter', 'exports']
    ,cols: [[
      {type: 'numbers', fixed: 'left'}
      ,{field: 'companyName', width: 200,align: 'center', title: '供水公司'}
      ,{field: 'userNo', width: 150,align: 'center', title: '户号'}
      ,{field: 'statusDict', title: '状态', templet: '#buttonTpl', width: 80, align: 'center'}
      ,{field: 'applicant', width: 100, title: '申请人'}
      ,{field: 'appdateString', width: 120, title: '报修时间'}
      ,{field: 'expireString', width: 120, title: '过期时间'}
      ,{field: 'shwAddress', minWidth: 100, title: '发生地址'}
      ,{field: 'fynrDict', width: 100, title: '反映内容'}
      ,{field: 'contactValue', width: 120, title: '联系电话'}
      ,{field: 'xzqName', width: 120,hide:true, title: '反应区名'}
      ,{field: 'bxwzDict', width: 120,hide:true, title: '报修位置'}
      ,{field: 'certTypeDict', width: 120, hide:true,title: '证件类型'}
      ,{field: 'certNumber', width: 120, hide:true,title: '证件号码'}
      ,{field: 'applyNo', width: 120, hide:true,title: '统一审批编码'}
      ,{field: 'repairComment', width: 120, hide:true,title: '备注'}
      ,{field:'deal', title:'是否处理', width:110, templet: '#test-table-checkboxTpl', unresize: true}
      ,{title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-system-order'}
    ]]
    ,page: true
    ,limit: 10
    ,limits: [10, 15, 20, 25, 30]
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

 table.on('toolbar(test-table-toolbar)', function(obj){
     switch(obj.event){
       case 'getCheckData':
       break;
       case 'getCheckLength':
       break;
       
       case 'getExportkData':
    	   window.location.href="/incident/handle/export?incidentType=RepairLeakWater";
       break;
       
       case 'isAll':
       break;
     };
   });
 
 
  //监听工具条
  table.on('tool(LAY-app-system-order)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '报修'
        ,content: '/home/repairs/detail?incidentId='+data.incidentId
        ,area: ['95%', '95%']
        ,btn: ['办结','退单','延期']
        ,yes: function(index, layero){
        	layer.load(2);
        	var iframeWindow = window['layui-layer-iframe'+ index]
            ,submitID = 'LAY-app-workorder-submit'
            ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
              var field = data.field; //获取提交的字段
              //alert(field.incidentId);
              var param = {
      				incidentId: field.incidentId,
      				status: "IncidentStatus.Finish",
      				description: ""
      			};
              var payload = JSON.stringify(param);
              //提交 Ajax 成功后，静态更新表格中的数据
              $.ajax({
      			type:"post",
      			contentType: 'application/json;charset=utf-8',
      			dataType:"json",
      			url:"/incident/handle/status",
      			data:payload,
                  cache: false,
                  async: true,
      			success:function(res){
      				layer.closeAll('loading');
      				console.log(res);
      				if(res.success){
      		        	layer.alert("申请单状态修改成功。");
      		        	table.reload('LAY-user-front-submit'); //数据刷新
      		        	layer.close(index); //关闭弹层
      		        	window.location.reload();
      				}else{
      		        	layer.alert(res.errMessage);
      				}
      			}
      		});
              
            });  
            submit.trigger('click');
        }
        ,btn2: function(index, layero){
    		var incidentId = data.incidentId;
        	layer.prompt({title: '请输入退单原因', formType: 2}, function(text, index2){
        		layer.load(2);
                //监听提交
                  var param = {
          				incidentId: incidentId,
          				status: "IncidentStatus.Return",
          				description: text
          			};
                  var payload = JSON.stringify(param);
                  //提交 Ajax 成功后，静态更新表格中的数据
                  $.ajax({
          			type:"post",
          			contentType: 'application/json;charset=utf-8',
          			dataType:"json",
          			url:"/incident/handle/status",
          			data:payload,
                      cache: false,
                      async: false,
          			success:function(res){
          				layer.closeAll('loading');
          				if(res.success){
          		        	layer.alert("申请单状态修改成功。");
          		        	layer.close(index2);
          		        	window.location.reload();
          		        	table.reload('LAY-user-front-submit'); //数据刷新
          				}else{
          		        	layer.alert(res.errMessage);
          				}
          			}
          		});
        	});
        	return false;
        }
        ,btn3: function(index, layero){
        	var incidentId = data.incidentId;
        	layer.prompt({title: '请输入延期原因', formType: 2}, function(text, index2){
        		layer.load(2);
                //监听提交
                  var param = {
          				incidentId: incidentId,
          				status: "IncidentStatus.Defer",
          				description: text
          			};
                  var payload = JSON.stringify(param);
                  //提交 Ajax 成功后，静态更新表格中的数据
                  $.ajax({
          			type:"post",
          			contentType: 'application/json;charset=utf-8',
          			dataType:"json",
          			url:"/incident/handle/status",
          			data:payload,
                      cache: false,
                      async: false,
          			success:function(res){
          				layer.closeAll('loading');
          				if(res.success){
          		        	layer.alert("申请单状态修改成功。");
          		        	layer.close(index2);
          		        	window.location.reload();
          		        	table.reload('LAY-user-front-submit'); //数据刷新
          				}else{
          		        	layer.alert(res.errMessage);
          				}
          			}
          		});
        	});
        	return false;
        }
        ,success: function(layero, index){
        	
        }
      });
    }else{
    	var tr = $(obj.tr);
        layer.open({
            type: 2
            ,title: '转单'
            ,content: '/incident/handle/transfer/index?incidentId='+data.incidentId
            ,area: ['50%', '50%']
            ,btn: ['确认','取消']
            ,yes: function(index, layero){
            	var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'LAY-app-workorder-submit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
                //监听提交
                iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                  var field = data.field; //获取提交的字段
                  //alert(field.incidentId);
                  var param = {
          				incidentId: field.incidentId,
          				description: field.description,
          				companyCode: field.companyCode
          			};
                  var payload = JSON.stringify(param);
                  //提交 Ajax 成功后，静态更新表格中的数据
                  $.ajax({
          			type:"post",
          			contentType: 'application/json;charset=utf-8',
          			dataType:"json",
          			url:"/incident/handle/transfer",
          			data:payload,
                      cache: false,
                      async: true,
          			success:function(res){
          				console.log(res);
          				if(res.success){
          		        	layer.alert("申请单状态修改成功。");
          		        	table.reload('LAY-user-front-submit'); //数据刷新
          		        	layer.close(index); //关闭弹层
          		        	window.location.reload();
          				}else{
          		        	layer.alert(res.errMessage);
          				}
          			}
          		});
                  
                });  
                submit.trigger('click');
            }
            ,success: function(layero, index){
            	
            }
          });
    }
  });

  exports('plumbingLeakIndex', {})
});