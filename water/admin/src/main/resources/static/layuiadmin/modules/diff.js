layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  // 用户管理
  table.render({
	    elem: '#LAY-user-back-navigation'
	    ,url: '/reconciliation/diff/query'//模拟接口
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
	    	,{field: 'orderId',  title: '订单号',templet: function(d){
				  if(d.bankNo == "UWC"){//CIS订单号
					  return d.flowNo;
				  }else{
					  return d.orderId;
				  }
			  }}
	    	,{field: 'money',  title: '金额',templet: function(d){
				  if(d.bankNo == "UWC"){//CIS金额
					  return d.payMoney;
				  }else{
					  return d.money;
				  }
			  }}
	    	,{field: 'nWeiyuej',  title: '违约金'}
	    	,{field: 'sfrq',  title: '收费日期',templet: function(d){
				  if(d.bankNo== "UWC"){//CIS金额
					  return d.payDate;
				  }else{
					  return d.sfrq;
				  }
			  }}
	    	,{field: 'userNo',  title: '户号',templet: function(d){
				  if(d.bankNo == "UWC"){//CIS金额
					  return d.acctId;
				  }else{
					  return d.userNo;
				  }
			  }}
	    	,{field: 'status',  title: '清算状态',templet: function(d){
				  if(d.bankNo == "UWC"){//CIS金额
					  if(d.qsStatus == "02"){
						  return "取消订单";
					  }else if(d.qsStatus == "03"){
						  return "申请退款";
					  }else if(d.qsStatus == "04"){
						  return "销单并退款";
					  }else{
						  return "未知状态码";
					  }
				  }else{
					  if(d.status == "02"){
						  return "取消订单";
					  }else if(d.status == "03"){
						  return "申请退款";
					  }else if(d.status == "04"){
						  return "销单并退款";
					  }else{
						  return "未知状态码";
					  }
				  }
			  }}
	    	,{field: 'isRepeat',  title: '是否重复',templet: function(d){
				  if(d.isRepeat == "0"){//CIS金额
					  return "不重复";
				  }else{
					  return "重复";
				  }
			  }}
		  ,{field: 'orderStatus',  title: '订单状态',templet: function(d){
			  if(d.orderStatus == "Normal"){
				  return "正常";
			  }else{
				  return "异常订单";
			  }
		  }}
		  ,{field: 'operId', title: '操作员'}
	    ]]
	    ,text:{
			none: '暂无相关数据'
		}
	  });
  
  //监听工具条

  exports('diffIndex', {})
});