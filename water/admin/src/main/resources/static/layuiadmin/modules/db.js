layui.define(['table', 'form','util'], function(exports){
  var $ = layui.$
  ,table=layui.table
  ,util = layui.util
  ,form = layui.form;
  form.on('submit(LAY-db-back-search)', function(obj){
	var colsData=[];
	var dataTable=[];
	var field=obj.field;
	console.log(field.db);
	if(field.db===""){
		console.log("test");
		layer.open({
			content:'请选择数据库'
		})
	}else{
			if(field.name===""||field.pass===""){
				layer.open({
					content:'用户名和密码不能为空'
				})
			}else{
				if(field.db==="mcsrpr"){
					if(field.name==="mcsrpr"&&field.pass==="Mcsrpr2019"){
						if(field.sql===""){
							layer.open({
								content:'请输入查询语句'
							})
						}else{
							
							$.ajax({
								url:'/right/db/query?sql='+field.sql+'&name='+field.name+'&pass='+field.pass,
								success:function(res){
									console.log(res.cols);
									for(var i=0;i<res.cols.length;i++){
										colsData[i]={field:res.cols[i],title:res.cols[i]};
									}
									console.log(colsData);
									table.reload('LAY-db-back-role', {
						                url:'/right/db/query'
						                ,where:{
						                	sql:field.sql,
						                	name:field.name,
						                	pass:field.pass
											
						                }
						                ,cols: [colsData]
						            });
								}
							});
							table.render({
								elem:'#LAY-db-back-role'
								,url:'/right/db/query'
								,where:{
									sql:field.sql,
									name:field.name,
									pass:field.pass
								}
								,cols:[colsData]
								,text:{
									none:'暂无相关数据',
									
								}
								,page:true
						        ,text:{
									none:'暂无相关数据!'
								}
								
								
							})
							
						}
					}else{
						layer.open({
							content:'用户名或密码不正确'
						})
					}
				}else{
					if(field.db==="mcsuw"){
						if(field.name==="mcsuw"&&field.pass==="Mcsuw2019"){
							if(field.sql===""){
								layer.open({
									content:'请输入查询语句'
								})
							}else{
								
								$.ajax({
									url:'/right/db/query?sql='+field.sql+'&name='+field.name+'&pass='+field.pass,
									success:function(res){
										console.log(res.cols);
										for(var i=0;i<res.cols.length;i++){
											colsData[i]={field:res.cols[i],title:res.cols[i]};
										}
										console.log(colsData);
										table.reload('LAY-db-back-role', {
							                url:'/right/db/query'
							                ,where:{
							                	sql:field.sql,
												name:field.name,
												pass:field.pass
							                }
							                ,cols: [colsData]
							            });
									}
								});
								table.render({
									elem:'#LAY-db-back-role'
									,url:'/right/db/query'
									,where:{
										sql:field.sql,
										name:field.name,
										pass:field.pass
									}
									,cols:[colsData]
									,text:{
										none:'暂无相关数据',
										
									}
									,page:true
							        ,text:{
										none:'暂无相关数据!'
									}
									
									
								})
								
							}
						}else{
							layer.open({
								content:'用户名或密码不正确'
							})
						}
					}else{
						if(field.db==="mcsmgm"){
							if(field.name==="mcsmgm"&&field.pass==="Mcsmgm2019"){
								if(field.sql===""){
									layer.open({
										content:'请输入查询语句'
									})
								}else{
									
									$.ajax({
										url:'/right/db/query?sql='+field.sql+'&name='+field.name+'&pass='+field.pass,
										success:function(res){
											console.log(res.cols);
											for(var i=0;i<res.cols.length;i++){
												colsData[i]={field:res.cols[i],title:res.cols[i]};
											}
											console.log(colsData);
											table.reload('LAY-db-back-role', {
								                url:'/right/db/query'
								                ,where:{
								                	sql:field.sql,
								                	name:field.name,
								                	pass:field.pass
								                }
								                ,cols: [colsData]
								            });
										}
									});
									table.render({
										elem:'#LAY-db-back-role'
										,url:'/right/db/query'
										,where:{
											sql:field.sql,
											name:field.name,
											pass:field.pass
										}
										,cols:[colsData]
										,text:{
											none:'暂无相关数据',
											
										}
										,page:true
								        ,text:{
											none:'暂无相关数据!'
										}
										
										
									})
									
								}
							}else{
								layer.open({
									content:'用户名或密码不正确'
								})
								
							}
						}else{
							if(field.db==="mcspay"){
								if(field.name==="mcspay"&&field.pass==="Mcspay2019"){
							
									if(field.sql===""){
										layer.open({
											content:'请输入查询语句'
										})
									}else{
										
										$.ajax({
											url:'/right/db/query?sql='+field.sql+'&name='+field.name+'&pass='+field.pass,
											success:function(res){
												console.log(res.cols);
												for(var i=0;i<res.cols.length;i++){
													colsData[i]={field:res.cols[i],title:res.cols[i]};
												}
												console.log(colsData);
												table.reload('LAY-db-back-role', {
									                url:'/right/db/query'
									                ,where:{
									                	sql:field.sql,
									                	name:field.name,
									                	pass:field.pass
									                }
									                ,cols: [colsData]
									            });
											}
										});
										table.render({
											elem:'#LAY-db-back-role'
											,url:'/right/db/query'
											,where:{
												sql:field.sql,
												name:field.name,
												pass:field.pass
											}
											,cols:[colsData]
											,text:{
												none:'暂无相关数据',
												
											}
											,page:true
									        ,text:{
												none:'暂无相关数据!'
											}
											
											
										})
										
									}
								}else{
									layer.open({
										content:'用户名或密码不正确'
									})
								}
							}
						}
					}
				}
			}
		}
  });
  
  
  exports('db', {})
});