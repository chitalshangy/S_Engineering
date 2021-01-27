<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Chital
  Date: 2020/12/7
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>失败</title>
    <script language="javascript">
        var times = 4;
        clock();
        function clock() {
            window.setTimeout('clock()', 1000);
            times = times - 1;
            time.innerHTML = times;
            if (times <= 0) {
                parent.location.reload();
            }
        }
    </script>
</head>
<body>
<h1 align="center"><label><s:property value="#request.tip"/></label></h1>
<h2 align="center">当前页面将会在<div id="time">3</div>秒后刷新</h2>
</body>
</html>
