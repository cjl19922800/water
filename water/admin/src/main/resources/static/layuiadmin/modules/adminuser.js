layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  // 用户管理
  table.render({
    elem: '#LAY-user-back-adminuser'
    ,url: '/right/adminuser/list'
    ,cols: [[
      {type: 'checkbox'}
      ,{field: 'userId', width: 100, title: '用户ID'}
      ,{field: 'userName', title: '用户名'}
      ,{field: 'displayName', title: '显示名'}
      ,{field: 'cellPhone', title: '手机'}
      ,{field: 'email', title: '邮箱'}
      ,{field: 'lastPasswordLogin', title: '上次登录时间'}
      ,{field: 'description', title: '描述'},
      ,{title: '操作', width: 145, align: 'center', toolbar: '#table-useradmin-admin'}
    ]]
    ,text: '数据加载出现异常！'
  });
  
  //监听工具条
  table.on('tool(LAY-user-back-adminuser)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('确定删除此角色？', function(index){
          $.ajax({
  			type:"post",
  			contentType: 'application/json;charset=utf-8',
  			dataType:"json",
  			url:"/right/adminuser/delete?id=" + data.id,
              cache: false,
              async: true,
  			success:function(res){
  				//var resultString = formatJson(data);
              	//$('#result').val(resultString);
              	//$('#result').val(JSON.stringify(data));
              	//console.log(data);
              	
  				console.log(res);
  				if(res.success){
  		        	layer.alert("删除成功。");
  		        	table.reload('LAY-user-back-adminuser'); //数据刷新
  		            layer.close(index); //关闭弹层
  				}else{
  		        	layer.alert("删除失败。");
  				}
  			}
  		});
        layer.close(index);
      });
    }else if(obj.event === 'edit'){
      var tr = $(obj.tr);
      console.log(data);

      layer.open({
        type: 2
        ,title: '编辑用户'
        ,content: '/right/adminuser/detail?id=' + data.id
        ,area: ['50%', '80%']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submit = layero.find('iframe').contents().find("#LAY-admin-user-edit-submit");

          //监听提交
          iframeWindow.layui.form.on('submit(LAY-admin-user-edit-submit)', function(data){
            var field = data.field; //获取提交的字段
            console.log(field);
            
          //提交 Ajax 成功后，静态更新表格中的数据
            var payload = JSON.stringify(field);
            
            //提交 Ajax 成功后，静态更新表格中的数据
            $.ajax({
    			type:"post",
    			contentType: 'application/json;charset=utf-8',
    			dataType:"json",
    			url:"/right/adminuser/update",
    			data:payload,
                cache: false,
                async: true,
    			success:function(res){
    				//var resultString = formatJson(data);
                	//$('#result').val(resultString);
                	//$('#result').val(JSON.stringify(data));
                	//console.log(data);
                	
    				console.log(res);
    				if(res.success){
    		        	layer.alert("管理员修改成功。");
    		        	table.reload('LAY-user-back-adminuser'); //数据刷新
    		            layer.close(index); //关闭弹层
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
      })
    }
  });

  exports('adminuser', {})
});