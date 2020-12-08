<%--
  Created by IntelliJ IDEA.
  User: Chital
  Date: 2020/12/8
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>

    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="static/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="static/css/login.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-login">
    <form action="login" method="post">
        <h1>
            <strong>智能会议室管理系统</strong>
            <em>Meeting Room Management System</em>
        </h1>

        <div class="layui-user-icon larry-login">
            <div class="layui-code-box">
                <input name="user.user_id" type="text" placeholder="账号" class="login_txtbx"/>
            </div>
        </div>
        <div class="layui-pwd-icon larry-login">
            <div class="layui-code-box">
                <input name="user.password" type="password" placeholder="密码" class="login_txtbx"/>
            </div>
        </div>
        <div class="layui-val-icon larry-login">
            <div class="layui-code-box">
                <input type="text" id="code" name="code" placeholder="验证码"class="login_txtbx">
            </div>
        </div>
        <div class="layui-submit larry-login">
            <input type="submit" value="登录" class="submit_btn"/>
        </div>
        <div class="layui-submit larry-login">
            <input type="button" value="注册" class="submit_btn" onclick="window.location.href='register.jsp'"/>
        </div>
    </form>
</div>
<script type="text/javascript" src="static/layui/layui.all.js"/>
<script type="text/javascript" src="static/js/login.js"/>
<script type="text/javascript" src="static/js/jparticle.jquery.js"/>
</body>
</html>
