<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZYJ
  Date: 2020/12/14
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>
</head>

<style>
    .hidden {
        display: none;
    }
</style>
<body style="text-align:center;">
<h1>恭喜您预约成功，您的预约流水号为: </h1>
<h1><s:property value="#request.reid"/></h1>
<img id="myLogo" class="hidden" src="images/Logo.png">
<div id="qrcode" style="width:600px;margin-left:auto;margin-right:auto;"></div>

<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script>
    var qrWidth = 300;
    var qrHeight = 300;
    var logoQrWidth = qrWidth / 4;
    var logoQrHeight = qrHeight / 4;
    $('#qrcode').qrcode({
        render: "canvas",    //设置渲染方式，有table和canvas
        text: "<s:property value="#request.reid"/>",
        width: qrWidth, //二维码的宽度
        height: qrHeight //二维码的高度
    })

    $("#qrcode canvas")[0].getContext('2d').drawImage($("#myLogo")[0], (qrWidth - logoQrWidth) / 2, (qrHeight - logoQrHeight) / 2, logoQrWidth, logoQrHeight);
</script>
</body>
</html>
