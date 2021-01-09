<%--
  Created by IntelliJ IDEA.
  User: ZYJ
  Date: 2020/12/30
  Time: 0:27
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
<form class="layui-form" action="emergencyReserve.action" method="post" onsubmit="return validateNum()">
    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" name="reserve.rephone" required lay-verify="required" placeholder="请输入"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">与会人数</label>
        <div class="layui-input-block">
            <input type="text" id="num" name="num" required lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
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
    function validateNum() {
        var num = document.getElementById("num").value;
        if (num <= 0) {
            alert("请输入正常的人数！！！");
            return false;
        }
        return true;
    }
</script>
</body>
</html>