<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZYJ
  Date: 2020/12/16
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>
<body class="layui-layout-body">
<label class="layui-word-aux"><s:property value="#request.tip"/></label>
<form class="layui-form" action="addConference" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">预约流水号</label>
        <div class="layui-input-block">
            <input type="text" name="conference.reserve.reid" required  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
</html>
