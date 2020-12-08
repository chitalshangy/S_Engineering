<%--
  Created by IntelliJ IDEA.
  User: Chital
  Date: 2020/12/7
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="static/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="static/css/login.css" media="all">
</head>
<body>
<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login">
    <h1>
        <strong>会议室预约管理系统</strong>
        <em>Meeting Room Management System</em>
    </h1>
    <div class="layui-user-icon larry-login">
        <input type="text" placeholder="账号" class="login_txtbx"/>
    </div>
    <div class="layui-pwd-icon larry-login">
        <input type="password" placeholder="密码" class="login_txtbx"/>
    </div>
    <div class="layui-val-icon larry-login">
        <div class="layui-code-box">
            <input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx">
            <img src="images/verifyimg.png" alt="" class="verifyImg" id="verifyImg" onclick="javascript:this.src='xxx'+Math.random();">
        </div>
    </div>
    <div class="layui-submit larry-login">
        <input type="button" value="立即登录" class="submit_btn"/>
    </div>
</div>
<script type="text/javascript" src="static/layui/layui.all.js"></script>
<script type="text/javascript" src="static/js/login.js"></script>
<script type="text/javascript" src="static/js/jparticle.jquery.js"></script>
<script type="text/javascript">
    $(function(){
        $(".layui-canvs").jParticle({
            background: "#141414",
            color: "#E6E6E6"
        });
    });
</script>
</body>
</html>
