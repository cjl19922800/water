layui.define(['table', 'form','util'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,util = layui.util
  ,form = layui.form;
  //日志列表
  table.render({
    elem: '#LAY-log-back-role'
    ,url: '/right/log/findByPage'//模拟接口
	,parseData:function(res){
		return {
			"code": res.code, //解析接口状态
			"msg": res.message, //解析提示文本
			"count": res.data.total, //解析数据长度
			"data": res.data.records //解析数据列表
		};
	}
	,page: true 
    ,cols: [[
	  {type:'numbers', width: '5%'}
	  ,{field: 'eventName', width: '15%',  title: '事件名称'}
	  ,{field: 'operation', width: '8%', title: '操作类型',templet: function(d){
		  if(d.operation == "query"){
			  return "查询";
		  }else if(d.operation == "apply"){
			  return "申请";
		  }else if(d.operation == "update"){
			  return "修改";
		  }else if(d.operation == "insert"){
			  return "新增";
		  }else if(d.operation == "delete"){
			  return "删除";
		  }else{
			  return d.operation;
		  }
	  }}
	  ,{field: 'field2', width: '10%', title: '操作对象/户号'}
	   ,{field: 'field1', title: '水司',width: '15%',templet: function(d){
			  if(d.field1 == "01"){
				  return "供水分公司";
			  }else if(d.field1 == "02"){
				  return "浦东威立雅自来水有限公司";
			  }else if(d.field1 == "03"){
				  return "浦东新区自来水有限公司";
			  }else if(d.field1 == "04"){
				  return "市北宝山自来水有限公司";
			  }else if(d.field1 == "05"){
				  return "自来水业务受理分公司";
			  }else{
				  return "";
			  }
		  }},
	   ,{field: 'result', title: '结果',width: '8%',templet: function(d){
			  if(d.result == "0"){
				  return "成功";
			  }else{
				  return "失败";
			  }
		  }}
	  ,{field: 'startTime',  title: '开始时间',width: '10%',templet:function(d){return util.toDateString(d.startTime, "yyyy-MM-dd HH:mm:ss");}}
	  ,{field: 'endTime',  title: '结束时间',width: '10%',templet:function(d){return util.toDateString(d.endTime, "yyyy-MM-dd HH:mm:ss");}}
	  ,{field: 'field6',width: '10%', title: '渠道'},
       ,{title: '操作', width: '9%', align: 'center',fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,text:{
		none: '暂无相关数据'
	}
  });
  //联动
  form.on('select(eventId)', function(obj){
	if(obj.value===""){
		$("select[name='eventType']").empty();
		var html = "<option value=''>全部</option>";
		$("select[name='eventType']").append(html); 
		form.render('select');
	}else{
		
		$.ajax({
			url:'/right/log/getEventTypeByEventId?eventId='+obj.value,
			success:function (res){
				console.log(res.data);
				//empty() 方法从被选元素移除所有内容
		        $("select[name='eventType']").empty();
				var html;
				$.each(res.data,function (key,val) {
					console.log(val.eventType);
					html += "<option value='" + val.eventType + "'>" + val.eventType + "</option>";
				});
				$("select[name='eventType']").append(html); 
				form.render('select');
			}
		})
	}
  });
  
  //监听工具条
  table.on('tool(LAY-log-back-role)', function(obj){
    var data = obj.data;
	if(obj.event === 'detail'){
		layer.open({
			type:2
			,btn:'确定'
			,area:  ['50%', '80%']
			,title:'日志详情'
			,content:'/right/log/detail?id='+data.id
			,success:function(layero,index){
				
				var body = layer.getChildFrame('body', index);
				var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	            
				console.log(body.html()) //得到iframe页的body内容
				body.find("input[name='startTime']").val(util.toDateString(data.startTime, "yyyy-MM-dd HH:mm:ss"));
				body.find("input[name='endTime']").val(util.toDateString(data.endTime, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='result']").val(util.toDateString(data.endTime, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='optime']").val(util.toDateString(data.optime, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='cdate']").val(util.toDateString(data.cdate, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='udate']").val(util.toDateString(data.udate, "yyyy-MM-dd HH:mm:ss")); 
            }
		});
    }
  });

  exports('log', {})
});