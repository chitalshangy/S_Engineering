<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZYJ
  Date: 2020/12/17
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>
<body>

<!--用户表格-->
<table class="layui-hide" id="TTTtest" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script>
    //定义全局变量$
    var $=layui.jquery;

    layui.use(['jquery','table'], function(){
        var load = layui.layer.load(0);// 加载时loading效果
        layui.layer.close(load); //加载效果

        var table = layui.table;

        //表格的渲染
        table.render({
            //指向的是表格的id
            elem: '#TTTtest'
            ,url:'reserveList.action'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title:'会议室预约情况表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'room', title:'会议室号', sort: true,templet: '<div>{{d.room.rid}}</div>'}
                ,{field:'date', title:'预约日期', sort: true}
                ,{field:'startTime', title:'开始时间', sort: true}
                ,{field:'endTime', title:'结束时间', sort: true}
            ]]
            ,page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });


    });
</script>

</body>
</html>
