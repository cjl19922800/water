layui.define(['table', 'form','util'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,util = layui.util
  ,form = layui.form;
  table.render({
    elem: '#aulog-back-search'
    ,url: '/home/transfer/queryList'//模拟接口
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
		,{field: 'shwCompanyName',  title: '水司'}
		,{field: 'incidentTypeName',  title: '申请单类型'}
		,{field: 'appdate',  title: '申请时间',templet:function(d){return util.toDateString(d.appdate, "yyyy-MM-dd HH:mm:ss");},sort:true}
		,{field: 'applicant',  title: '申请人'}
		,{field: 'transferCompanyName',  title: '转单公司'}
		,{field: 'userNo',  title: '户号'}
		,{field: 'applyNo',  title: '申请批次号'}
		,{field: 'source',  title: '申请单来源'}
		,{title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-system-order'}
    ]]
    ,text:{
		none: '暂无相关数据'
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
  table.on('tool(aulog-back-search)', function(obj){
    var data = obj.data;
	if(obj.event === 'detail'){
		var incidentType = data.incidentType;
		var url = "";
		if(incidentType == 'McsItemType.RepairLeakWaterResident' 
			|| incidentType == 'McsItemType.RepairLeakWaterCompany' 
			|| incidentType == 'McsItemType.RepairUseWaterResident' 
			|| incidentType == 'McsItemType.RepairUseWaterCompany' 
			|| incidentType == 'McsItemType.RepairWaterQualityResident' 
			|| incidentType == 'McsItemType.RepairWaterQualityCompany' 
			|| incidentType == 'McsItemType.RepairWaterMeterResident' 
			|| incidentType == 'McsItemType.RepairWaterMeterCompany' 
			|| incidentType == 'McsItemType.RepairOtherResidentCompany' 
			|| incidentType == 'McsItemType.RepairOtherCompany' 
		){//报修
			url = "/home/repairs/detail?incidentId="+data.incidentId;
		}else if(incidentType == 'McsItemType.ResidentApply'){//居民节水
			url = '/resiapply/detail?incidentId='+data.incidentId;
		}else if(incidentType == 'McsItemType.RepairCompanyTransfer'){//单位过户
			url = '/home/comtrans/detail?incidentId='+data.incidentId;
		}else if(incidentType == 'McsItemType.RepairResidentTransfer'){//居民过户
			url = '/home/resitrans/detail?incidentId='+data.incidentId
		}else if(incidentType == 'McsItemType.RepairWatermeterSplit'){//总表分装
			url = '/home/wmsplit/detail?incidentId='+data.incidentId
		}else{
			url = "";
		}
	      layer.open({
	          type: 2
	          ,title: '申请单详情'
	          ,content: url
	          ,area: ['95%', '95%']
	          ,btn: ['关闭']
	          ,yes: function(index, layero){
	            layer.close(index); //关闭弹层
	          }
	        });
    }
  });

  exports('transferIncident', {})
});