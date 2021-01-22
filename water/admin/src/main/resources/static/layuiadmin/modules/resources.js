layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  
  
  //搜索日志
  form.on('submit(LAY-log-back-search)', function(data){
	  var field=data.field;
		console.log(field);
		
     //执行重载
     table.reload('LAY-user-back-navigation', {
   	  where: field
		});
  });
  
  
  // 用户管理
  table.render({
	    elem: '#LAY-user-back-navigation'
	    ,url: '/admin/power/resources/query'//模拟接口
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
		  ,{field: 'name',  title: '资源名称'}
		  ,{field: 'url',  title: '资源URL'}
		  ,{field: 'modular',  title: '模块'}
		  ,{field: 'listResources',  title: '主页资源', templet: function(listResources){
			  if(listResources.listResources == true){
				  return "是";
			  }else{
				  return "否";
			  }
		  }}
		  ,{field: 'employ', title: '启用状态',templet: function(employ){
			  if(employ.employ == true){
				  return "启用";
			  }else{
				  return "未启用";
			  }
		  }}
		  ,{field: 'explain', title: '描述'}
	       ,{title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
	    ]]
	    ,text:{
			none: '暂无相关数据'
		}
	  });
  
  //监听工具条
  table.on('tool(LAY-user-back-navigation)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('确定删除此角色？', function(index){
        obj.del();
        layer.close(index);
      });
    }else if(obj.event === 'edit'){
      var tr = $(obj.tr);
      console.log(data);

      layer.open({
        type: 2
        ,title: '编辑导航'
        ,content: '/admin/power/resources/edit?id=' + data.id
        ,area: ['50%', '80%']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submit = layero.find('iframe').contents().find("#LAY-admin-user-add-submit");

          //监听提交
          iframeWindow.layui.form.on('submit(LAY-admin-user-add-submit)', function(data){
            var field = data.field; //获取提交的字段
            console.log(field);
            
          //提交 Ajax 成功后，静态更新表格中的数据
            var payload = JSON.stringify(field);
            
            //提交 Ajax 成功后，静态更新表格中的数据
            $.ajax({
    			type:"post",
    			contentType: 'application/json;charset=utf-8',
    			dataType:"json",
    			url:"/admin/power/resources/update",
    			data:payload,
                cache: false,
                async: true,
    			success:function(res){
    				if(res.success){
    		        	layer.alert("修改成功。");
    		        	table.reload('LAY-user-back-navigation'); //数据刷新
    		            layer.close(index); //关闭弹层
    				}else{
    		        	layer.alert("修改失败。");
    				}
    			}
    		});
          });  
          
          submit.trigger('click');
        }
        ,success: function(layero, index){
        
        }
      })
    }
  });

  exports('resources', {})
});