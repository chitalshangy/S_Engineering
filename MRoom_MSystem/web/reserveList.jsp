<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZYJ
  Date: 2020/12/14
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>
<body>

<!--用户表格-->
<table class="layui-hide" id="reserveTable" lay-filter="test"></table>

<!--修改会议状态-->
<div class="site-text" style="margin: 5%; display: none" id="box1" target="123">
    <form class="layui-form layui-form-pane" onsubmit="return false" id="booktype">
        <div class="layui-form-item">
            <label class="layui-form-label"> 会议状态</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="state" name=state><br>
            </div>
        </div>
    </form>
</div>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">管理与会人员</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">取消预约</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit1">结束</a>
</script>

<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">踢出会议</a>
</script>

<script type="text/javascript" src="layui/lay/modules/jquery.js"></script>

<script>
    //定义全局变量$
    var $ = layui.jquery;

    layui.use(['jquery', 'table'], function () {
        var load = layui.layer.load(0);// 加载时loading效果
        layui.layer.close(load); //加载效果

        var table = layui.table;

        //表格的渲染
        table.render({
            //指向的是表格的id
            elem: '#reserveTable'
            , url: 'myReserveList.action'
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '我预约的会议'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'reid', title: '预约流水号', sort: true}
                , {field: 'room', title: '会议室号', sort: true, templet: '<div>{{d.room.rid}}</div>'}
                , {field: 'state', title: '状态(0:已取消/1:进行中/2:已完结)', sort: true, width: 260}
                , {field: 'title', title: '主题', sort: true}
                , {field: 'date', title: '预约日期', sort: true}
                , {field: 'startTime', title: '开始时间', sort: true}
                , {field: 'endTime', title: '结束时间', sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', fixed: 'right', width: 250}
            ]]
            , page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data; //获得当前行数据
            console.log(data);
            if (obj.event === 'del') {
                layer.confirm('确定取消预约吗？', {title: '取消'}, function (index) {
                    //向服务端发送删除指令og
                    $.getJSON('deleteReserve.action', {reid: data.reid}, function (ret) {
                    });
                    layer.close(index);//关闭弹窗
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 1
                    , skin: 'layui-layer-molv'
                    , area: ['490px', '500px']
                    , title: ['与会人员信息', 'font-size:18px']
                    , btn: ['修改', '取消']
                    , shadeClose: true
                    , shade: 0
                    , maxmin: true
                    , content: '<table class="layui-hide" id="person" lay-filter="test1"></table>'
                    , success: function (layero, index) {
                        var id = data.reid;

                        var url = 'participantList.action?reid=' + id;

                        //表格的渲染
                        table.render({
                            //指向的是表格的id
                            elem: '#person'
                            , url: url
                            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                                title: '提示'
                                , layEvent: 'LAYTABLE_TIPS'
                                , icon: 'layui-icon-tips'
                            }]
                            , title: '与会人员'
                            , cols: [[
                                {type: 'checkbox', fixed: 'left'}
                                , {field: 'uid', title: '账号', sort: true}
                                , {field: 'uname', title: '姓名', sort: true}
                                , {field: 'uphone', title: '联系电话', sort: true}
                                , {fixed: 'right', title: '操作', toolbar: '#barDemo1', fixed: 'right'}
                            ]]
                            , page: true
                        });

                        //头工具栏事件
                        table.on('toolbar(test1)', function (obj) {
                            var checkStatus = table.checkStatus(obj.config.id);
                            switch (obj.event) {
                                case 'getCheckData':
                                    var data = checkStatus.data;
                                    layer.alert(JSON.stringify(data));
                                    break;
                                case 'getCheckLength':
                                    var data = checkStatus.data;
                                    layer.msg('选中了：' + data.length + ' 个');
                                    break;
                                case 'isAll':
                                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                                    break;
                                //自定义头工具栏右侧图标 - 提示
                                case 'LAYTABLE_TIPS':
                                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                                    break;
                            }
                            ;
                        });

                        //监听行工具事件
                        table.on('tool(test1)', function (obj) {
                            var data1 = obj.data; //获得当前行数据
                            console.log(data);
                            if (obj.event === 'del') {
                                layer.confirm('确定踢出会议吗？', {title: '踢出'}, function (index1) {
                                    //向服务端发送删除指令og
                                    $.getJSON('deleteConferenceByManager.action', {uid: data1.uid}, function (ret) {
                                    });
                                    layer.close(index1);//关闭弹窗
                                });

                            }
                        });
                    }, yes: function (index, layero) {
                        layer.close(index);//关闭弹窗
                    }
                });
            } else if (obj.event === 'edit1') {
                $.getJSON('updateReserve.action', {
                    reid: data.reid,
                });
            }
        });
    });
</script>
</body>
</html>