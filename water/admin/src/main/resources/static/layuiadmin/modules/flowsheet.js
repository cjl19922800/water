layui.define(['table', 'form','util'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,util = layui.util
  ,form = layui.form;
  //日志列表
  table.render({
    elem: '#LAY-flowsheet-back-role'
    ,url: '/right/flowsheet/getFailed'//模拟接口
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
		,{field: 'flowsheetId',  title: '流水单id'},
		,{field: 'flowsheetDetailId',  title: '流水单详情id'}
	  ,{field: 'money',  title: '支付金额'}
	   ,{field: 'daystring', title: '支付成功日期'}
	   ,{field: 'orderStatus', title: '订单状态'}
	    ,{field: 'status', title: '结果'}
	   ,{field: 'cdate', title: '创建时间',templet:function(d){return util.toDateString(d.cdate,"yyyy-MM-dd HH:mm:ss");},sort:true}
       ,{title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
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
  table.on('tool(flowsheet-back-search)', function(obj){
    var data = obj.data;
	if(obj.event === 'detail'){
		layer.open({
			type:2
			,btn:'确定'
			,area:  ['50%', '80%']
			,title:'流水单详情'
			,content:'/right/flowsheet/getFailed?id='+data.flowsheetDetailId
			,success:function(layero,index){
				
				var body = layer.getChildFrame('body', index);
				var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	            
				console.log(body.html()) //得到iframe页的body内容
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

  exports('flowsheet', {})
});