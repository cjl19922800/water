layui.define(['table', 'form','util'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,util = layui.util
  ,form = layui.form;
  //日志列表
  table.render({
    elem: '#aulog-back-search'
    ,url: '/right/aulog/getByPage'//模拟接口
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
		{field: 'requestUri',  title: '请求uri'}
		,{field: 'requestIp',  title: '请求ip'}
		,{field: 'status',  title: '状态'}
		,{field: 'result',  title: '结果',templet: function(d){
			  if(d.result == "0"){
				  return "成功";
			  }else{
				  return "失败";
			  }
		  }}
		,{field: 'startTime',  title: '起始时间',templet:function(d){return util.toDateString(d.startTime, "yyyy-MM-dd HH:mm:ss");}}
		,{field: 'endTime',  title: '截止时间',templet:function(d){return util.toDateString(d.endTime, "yyyy-MM-dd HH:mm:ss");}}
		,{title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-aulog'}
    ]]
    ,text:{
		none: '暂无相关数据'
	}
  });

  
  //监听工具条
  table.on('tool(aulog-back-search)', function(obj){
    var data = obj.data;
	if(obj.event === 'detail'){
		layer.open({
			type:2
			,btn:'确定'
			,area:  ['50%', '80%']
			,title:'日志详情'
			,content:'/right/aulog/detail?id='+data.id
			,success:function(layero,index){
				
				var body = layer.getChildFrame('body', index);
				var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	            
				console.log(body.html()) //得到iframe页的body内容
				body.find("input[name='startTime']").val(util.toDateString(data.startTime, "yyyy-MM-dd HH:mm:ss"));
				body.find("input[name='endTime']").val(util.toDateString(data.endTime, "yyyy-MM-dd HH:mm:ss"));
				
            }
		});
    }
  });

  exports('aulog', {})
});