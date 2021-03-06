<%--
  Created by IntelliJ IDEA.
  User: ZYJ
  Date: 2020/12/14
  Time: 12:57
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
<form class="layui-form" action="addReserve.action" method="post" onsubmit="return validateDate()">
    <div class="layui-form-item">
        <label class="layui-form-label">会议室编号</label>
        <div class="layui-input-block">
            <input type="text" name="reserve.rid" required lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" name="reserve.rephone" required lay-verify="required" placeholder="请输入"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">会议主题</label>
        <div class="layui-input-block">
            <input type="text" name="reserve.title" required lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">预定日期</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="date" name="reserve.date" required lay-verify="required"
                   placeholder="请输入">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="stime" name="reserve.startTime" required lay-verify="required"
                   placeholder="请输入">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="etime" name="reserve.endTime" required lay-verify="required"
                   placeholder="请输入">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">开放加入</label>
        <div class="layui-input-block">
            <input type="checkbox" checked="" name="reserve.open" lay-skin="switch" lay-filter="switchTest"
                   lay-text="1|0">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, laydate = layui.laydate;
        laydate.render({
            elem: '#date' //指定元素
        });

        laydate.render({
            elem: '#stime'
            , type: 'time'
        });

        laydate.render({
            elem: '#etime'
            , type: 'time'
        });
    });

    function validateDate() {
        var tmpp = document.getElementById("date").value;
        var date = new Date(tmpp.replace(/\-/g, "\/"));

        var tmp = new Date();
        var todaytmp = tmp.toLocaleDateString();
        var today = new Date(todaytmp.replace(/\-/g, "\/"));

        var stime = document.getElementById("stime").value;
        var etime = document.getElementById("etime").value;
        if (date.getTime() < today.getTime()) {
            alert("请选择正确的日期！！！");
            return false;
        }
        if (stime >= etime) {
            alert("请选择正确的时间段！！！");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
