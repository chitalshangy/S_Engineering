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
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="css/login.css" media="all">
</head>
<body>
<div class="layui-canvs"></div>
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
                <input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx">

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
<script type="text/javascript" src="layui/lay/dest/layui.all.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/jparticle.jquery.js"></script>
<script type="text/javascript">
    var code;
    function createCode(){
        code = "";
        var codeLength = 4;//验证码的长度
        var checkCode = document.getElementById("new_code");
        var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
            'S','T','U','V','W','X','Y','Z');//随机数
        for(var i = 0; i < codeLength; i++) {//循环操作
            var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）
            code += random[index];//根据索引取得随机数加到code上
        }
        checkCode.value = code;//把code值赋给验证码
    }
    function validate(){
        var inputCode = document.getElementById("code").value.toUpperCase(); //取得输入的验证码并转化为大写
        if(inputCode.length <= 0) { //若输入的验证码长度为0
            alert("请输入验证码！"); //则弹出请输入验证码
            return false;
        }else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
            alert("验证码输入错误！"); //则弹出验证码输入错误
            createCode();//刷新验证码
            document.getElementById("Captcha").value = "";//清空文本框
            return false;
        }else { //输入正确时
            alert("登录成功,正在跳转...");
        }
        return true;
    }
</script>

</body>
</html>
