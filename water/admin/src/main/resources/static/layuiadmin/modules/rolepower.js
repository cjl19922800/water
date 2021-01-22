

layui.define(['table', 'form'], function(exports){
  var roleId = document.getElementById("roleId").value;
  console.log(roleId);
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;
  //角色管理
  table =  $.extend(table, {config: {checkName: 'checked'}});
  table.render({
    elem: '#LAY-user-back-rolepower'
    ,url: '/right/role/queryPower?roleId='+roleId //模拟接口
    ,cols: [[
    	{type:'checkbox'}
    	 ,{field: 'name',  title: '资源名称'}
		  ,{field: 'url',  title: '资源URL'}
		  ,{field: 'modular',  title: '模块'}
		  ,{field: 'explain', title: '描述'}
    ]]
    ,text: '数据加载出现异常！'
  });
  
});