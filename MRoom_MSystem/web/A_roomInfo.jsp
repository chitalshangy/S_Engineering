<%--
  Created by IntelliJ IDEA.
  User: Chital
  Date: 2020/12/15
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会议室管理</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>
<body>
<!--主体，会议室表格-->
<table class="layui-hide" id="roomTable" lay-filter="test"></table>

<!--头部三个按钮的前端-->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<!--编辑以及删除的前端-->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript" src="layui/lay/modules/jquery.js"></script>

<script>
    //定义全局变量$
    var $ = layui.jquery;

    layui.use(['jquery', 'table'], function () {
        var load = layui.layer.load(0);// 加载时loading效果
        layui.layer.close(load); //加载效果

        var table = layui.table;

        //表格主体的渲染
        table.render({
            //指向的是表格的id
            elem: '#roomTable'
            , url: 'zpjsonRoomList.action'
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '会议室表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'rid', title: '编号', sort: true}
                , {field: 'rnum', title: '支持人数', sort: true}
                , {field: 'rstate', title: '是否可用', sort: true}
                , {field: 'raddress', title: '地址', sort: true}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
            , page: true
        });

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
            };
        });

        table.on('tool(test)', function (obj) {
            var data = obj.data; //获得当前行数据
            var urlex = "${pageContext.request.contextPath}";
            var tr = obj.tr//当前行tr 的  DOM对象
            console.log(data);
            if (obj.event === 'del') {
                layer.confirm('确定删除吗？', {title: '删除'}, function (index) {
                    //向服务端发送删除指令og
                    $.getJSON('null', {uid: data.uid}, function (ret) {
                    });
                    layer.close(index);//关闭弹窗
                    table.reload('roomTable', {page: {curr: 1}, where: {time: new Date()}});
                });

            } else if (obj.event === 'edit') {
                //这里是编辑
                layer.open({
                    type: 1 //Page层类型
                    , skin: 'layui-layer-molv'
                    , area: ['380px', '270px']
                    , title: ['编辑会议室信息', 'font-size:18px']
                    , btn: ['确定', '取消']
                    , shadeClose: true
                    , shade: 0 //遮罩透明度
                    , maxmin: true //允许全屏最小化
                    , content: $('#box3')  //弹窗id
                    , success: function (layero, index) {
                        $('#uname').val(data.uname);
                        $('#upassword').val(data.upassword);
                        $('#uidentity').val(data.uidentity);
                    }, yes: function (index, layero) {
                        $.getJSON('null', {
                            uname: $('#uname').val(),
                            upassword: $('#upassword').val(),
                            uidentity: $('#uidentity').val(),
                            uid: data.uid,
                        });
                        layer.close(index);//关闭弹窗
                        table.reload('roomTable', {page: {curr: 1}, where: {time: new Date()}});
                    }
                });
            }
        });
    });
</script>
</body>
</html>