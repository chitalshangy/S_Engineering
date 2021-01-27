<%--
  Created by IntelliJ IDEA.
  User: Chital
  Date: 2021/1/24
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录失败</title>
    <script language="javascript">
        var times = 4;
        clock();

        function clock() {
            window.setTimeout('clock()', 1000);
            times = times - 1;
            time.innerHTML = times;
            if (times == 0) {
                window.location = "login.jsp";
            }
        }
    </script>
</head>
<body>
<h2 align="center">登录失败，请输入正确的账号或密码</h2>
<h2 align="center">将在
    <div id="time">3</div>
    秒后跳转登录界面
</h2>
<h2 align="center">如果没有跳转，请<a href="login.jsp">点击</a></h2>
</body>
</html>
