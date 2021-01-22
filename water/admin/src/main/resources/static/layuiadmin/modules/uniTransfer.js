
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
        ,url: '/home/resitrans/list?type=McsItemType.UniTransfer' //模拟接口
        ,cols: [[
            {type: 'numbers', fixed: 'left'}
            ,{field: 'companyName', minWidth: 100, title: '供水公司'}
            ,{field: 'userNo', minWidth: 100, title: '户号'}
            ,{field: 'statusDict', title: '状态', templet: '#buttonTpl', width: 100, align: 'center'}
            ,{field: 'applicant', minWidth: 100, title: '申请人'}
            ,{field: 'appdateString', minWidth: 100, title: '申请日期'}
            ,{field: 'expireString', width: 120, title: '过期时间'}
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
            ,{field: 'shwAddress', width: 210, title: '房产地址'}
            ,{field: 'contactValue', minWidth: 100, title: '联系电话'}
            ,{field: 'bdczl', minWidth: 100, title: '不动产坐落'}
            ,{field: 'bdcdbrq', minWidth: 100, title: '不动产登记簿日期'}
            ,{field:'deal', title:'是否处理', width:110, templet: '#test-table-checkboxTpl', unresize: true}
            ,{title: '操作', width: 175, align: 'center', fixed: 'right', toolbar: '#table-system-order'}
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
                ,title: '联合过户'
                ,content: '/home/resitrans/detail?incidentId='+data.incidentId
                ,area: ['95%', '95%']
                ,btn: ['办结','退单','延期']
                ,yes: function(index, layero){
                	layer.load(2);
                      var param = {
              				incidentId: data.incidentId,
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
              		        	table.reload('LAY-app-system-order'); //数据刷新
              		        	layer.close(index); //关闭弹层
              		        	window.location.reload();
              				}else{
              		        	layer.alert(res.errMessage);
              				}
              			}
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
                  		        	table.reload('LAY-app-system-order'); //数据刷新
                  				}else{
                  		        	layer.alert(res.errMessage);
                  				}
                  			}
                  		});
                        submit.trigger('click');
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
                  		        	table.reload('LAY-app-system-order'); //数据刷新
                  				}else{
                  		        	layer.alert(res.errMessage);
                  				}
                  			}
                  		});
                        submit.trigger('click');
                	});
                	return false;
                }
                ,success: function(layero, index){

                }
            });
        }else if(obj.event === 'down'){
        	window.location.href="/incident/handle/downSale?incident_id="+data.incidentId
        }
    });

    exports('uniTransfer', {})
});