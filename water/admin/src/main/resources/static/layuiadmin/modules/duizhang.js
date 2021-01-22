layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  // 用户管理
  table.render({
	    elem: '#LAY-user-back-navigation'
	    ,url: '/reconciliation/duizhang/query'//模拟接口
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
	    	{type: 'numbers', fixed: 'left'}
	    	,{field: 'orderId',  title: '订单号'}
	    	,{field: 'money',  title: '金额'}
	    	,{field: 'status',  title: '状态'}
	    	,{field: 'sbh',  title: '户号'}
	    	,{field: 'daystring',  title: '支付日期'}
		  ,{field: 'payChannel',  title: '支付渠道',templet: function(d){
			  if(d.payChannel == "uppaydirect"){
				  return "银联";
			  }else if(d.payChannel == "weixin"){
				  return "微信";
			  }else if(d.payChannel == "zhifubao"){
				  return "支付宝";
			  }else{
				  return d.payChannel;
			  }
		  }}
		  ,{field: 'orderStatus', title: '订单状态',templet: function(d){
			  if(d.orderStatus == "Normal"){
				  return "正常订单";
			  }else{
				  return "异常订单";
			  }
		  }}
	    ]]
	    ,text:{
			none: '暂无相关数据'
		}
	  });
  
  //监听工具条

  exports('duizhangIndex', {})
});