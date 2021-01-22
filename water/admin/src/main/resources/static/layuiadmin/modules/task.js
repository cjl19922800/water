layui.define(['table', 'form','util'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,util = layui.util
  ,form = layui.form;
  //日志列表
  table.render({
    elem: '#task-back-search'
    ,url: '/right/taskLog/getTask'//模拟接口
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
		{type:'numbers'}
	  ,{field: 'eventid',  title: '事件类型',templet: function(d){
		  if(d.eventid == "MCS.Task.huipan"){
			  return "回盘";
		  }else if(d.eventid == "MCS.Task.duizhang"){
			  return "对账";
		  }else if(d.eventid == "MCS.Task.diff"){
			  return "差异对账";
		  }else{
			  return "";
		  }
	  }}
	   ,{field: 'optarget', title: '公司',templet: function(d){
		   return queryCompanyName(d.optarget);
		  }}
	   ,{field: 'result', title: '流程节点',templet: function(d){
			  if(d.result == "OK"){
				  return "处理成功";
			  }else if(d.result == "FAILURE"){
				  return "重大异常";
			  }else if(d.result == "START"){
				  return "流程开始";
			  }else if(d.result == "END"){
				  return "流程结束";
			  }else if(d.result == "INFO"){
				  return "中间流程";
			  }else if(d.result == "ERROR"){
				  return "异常";
			  }else{
				  return "";
			  }
		  }}
	   ,{field: 'startTime', title: '起始日期',templet:function(d){return util.toDateString(d.startTime,"yyyy-MM-dd HH:mm:ss");},sort:true}
	   ,{field: 'endTime', title: '截止日期',templet:function(d){return util.toDateString(d.endTime,"yyyy-MM-dd HH:mm:ss");},sort:true}
       ,{title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,text:{
		none: '暂无相关数据'
	}
  });
  
  function queryCompanyName(dictItem){
	  	var b = "";
	  	$.ajax({
	  		type:"post",
	  		contentType: 'application/json;charset=utf-8',
	  		dataType:"json",
	  		url:"/water/queryCompanyName?dictItem="+dictItem,
	  		cache: false,
	  		async: false,
	  		dataType:"json",  
	  		success:function(res){
	  			b = res.dataString;
	  		}
	  	});
	  	return b;
	  }  
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
  table.on('tool(task-back-search)', function(obj){
    var data = obj.data;
	if(obj.event === 'detail'){
		layer.open({
			type:2
			,btn:'确定'
			,area:  ['50%', '80%']
			,title:'task日志详情'
			,content:'/right/taskLog/datail?taskId='+data.id
			,success:function(layero,index){
				
				var body = layer.getChildFrame('body', index);
				var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	            
				body.find("input[name='daystring']").val(util.toDateString(data.daystring, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='endTime']").val(util.toDateString(data.endTime, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='result']").val(util.toDateString(data.endTime, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='optime']").val(util.toDateString(data.optime, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='cdate']").val(util.toDateString(data.cdate, "yyyy-MM-dd HH:mm:ss"));
				//body.find("input[name='udate']").val(util.toDateString(data.udate, "yyyy-MM-dd HH:mm:ss")); 
            }
		});
    }
  });

  exports('task', {})
});