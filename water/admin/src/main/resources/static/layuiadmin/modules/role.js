layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //角色管理
  table.render({
    elem: '#LAY-user-back-role'
    ,url: '/right/role/list' //模拟接口
    ,cols: [[
    	{type:'checkbox',field: 'id'}
    	,{type:'numbers'}
      ,{field: 'roleName', width: '30%', title: '角色名称'}
      ,{field: 'description', title: '描述'}
      ,{title: '权限设置', width: 80, align: 'center', fixed: 'right', toolbar: '#table-useradmin-power'}
      ,{title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,text: '数据加载出现异常！'
  });
  
  //监听工具条
  table.on('tool(LAY-user-back-role)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('确定删除此角色？', function(index){
        obj.del();
        layer.close(index);
      });
    }else if(obj.event === 'edit'){
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '编辑角色'
        ,content: '/right/role/detail?id=' + data.id
        ,area: ['500px', '480px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submit = layero.find('iframe').contents().find("#LAY-user-role-submit");

          //监听提交
          iframeWindow.layui.form.on('submit(LAY-user-role-submit)', function(data){
        	var arr = new Array();
            $(layero).find('iframe').contents().find("input:checkbox[name='companyPowers']:checked").each(function(i){
              arr[i] = $(this).val();
            });
            data.field.companyPowers = arr.join(",");//将数组合并成字符串
            var field = data.field; //获取提交的字段
            console.log(field);
            //提交 Ajax 成功后，静态更新表格中的数据
            var payload = JSON.stringify(field);
            //提交 Ajax 成功后，静态更新表格中的数据
            $.ajax({
    			type:"post",
    			contentType: 'application/json;charset=utf-8',
    			dataType:"json",
    			url:"/right/role/update",
    			data:payload,
                cache: false,
                async: true,
    			success:function(res){
    				console.log(res);
    				if(res.success){
    		        	layer.alert("角色修改成功。");
    		            table.reload('LAY-user-back-role'); //数据刷新
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
    }else if(obj.event === 'setPower'){
        var tr = $(obj.tr);
        layer.open({
          type: 2
          ,title: '角色权限设置'
          ,content: '/right/role/powerSet?id=' + data.id
          ,area: ['80%', '70%']
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
        	  var iframeWindow = window['layui-layer-iframe' + index];
         	  var checkStatus = iframeWindow.layui.table.checkStatus('LAY-user-back-rolepower')
              ,checkData = checkStatus.data; //得到选中的数据
         	 console.log(checkData);
              if(checkData.length === 0){
            	  layer.close(index);
              }else{
            	  var roleId = data.id;
            	  var payload = JSON.stringify(checkData);
            	  $.ajax({
          			type:"post",
          			contentType: 'application/json;charset=utf-8',
          			dataType:"json",
          			url:"/right/role/power/insert?id="+roleId,
          			data:payload,
                      cache: false,
                      async: true,
          			success:function(res){
          				if(res.success){
          		        	layer.alert("权限新增成功。");
          		            table.reload('LAY-user-back-role'); //数据刷新
          		            layer.close(index); //关闭弹层
          				}else{
          		        	layer.alert("权限新增失败。");
          		        	layer.close(index); //关闭弹层
          				}
          			}
          		});
              }
          }
          ,success: function(layero, index){
          }
        })
    }
  });

  exports('role', {})
});