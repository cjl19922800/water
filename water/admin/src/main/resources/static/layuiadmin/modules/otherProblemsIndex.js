layui.define(['table', 'form', 'element'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form
  ,element = layui.element;

  table.render({
    elem: '#LAY-app-system-order'
    ,url: layui.setter.base + 'json/repairs/otherProblemsIndex.json' //模拟接口
    ,cols: [[
      {type: 'numbers', fixed: 'left'}
      ,{field: 'accept_company', minWidth: 100, title: '供水公司'}
      ,{field: 'status', title: '状态', templet: '#buttonTpl', width: 100, align: 'center'}
      ,{field: 'applicant', width: 100, title: '申请人'}
      ,{field: 'appdate', minWidth: 100, title: '申请日期', templet:'<div>{{ layui.laytpl.toDateString(d.appdate) }}</div>'}
      ,{field: 'repairsLocation', minWidth: 100, title: '报修位置'}
      ,{field: 'repairsContent', minWidth: 100, title: '报修内容'}
      ,{field: 'description', minWidth: 100, title: '描述'}
      ,{title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-system-order'}
    ]]
    ,page: true
    ,limit: 10
    ,limits: [10, 15, 20, 25, 30]
  ,text: {
	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
	}
    ,done: function(){
      element.render('progress')
    }
  });

  //监听工具条
  table.on('tool(LAY-app-system-order)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '编辑报修单'
        ,content: 'otherProblemsView.html'
        ,area: ['95%', '95%']
        ,btn: ['办结','处理中','退单']
        ,yes: function(index, layero){
          layer.close(index); //关闭弹层
        }
        ,btn2: function(index, layero){
          layer.close(index); //关闭弹层
        }
        ,btn3: function(index, layero){
          layer.close(index); //关闭弹层
        }
        ,success: function(layero, index){

        }
      });
    }
  });

  exports('otherProblemsIndex', {})
});