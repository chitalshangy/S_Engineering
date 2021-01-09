<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZYJ
  Date: 2020/12/14
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="qrcode.js"></script>
</head>
<body>
恭喜您预约成功，您的预约流水号为<s:property value="#request.reid"/><p>
<div id="myCode"></div>
<script>
    new QRCode(document.getElementById('myCode'), '<s:property value="#request.reid"/>');
</script>
</body>
</html>
