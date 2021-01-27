<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <title>html5调用摄像头实现拍照</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body style="padding: 0;margin: 0;background: url(images/back.jpg) no-repeat ;background-size: 100% 100%; background-color:rgba(0, 0, 0, 0.5);">
<h1 align="center" font-family:Serif>
    <marquee scrollAmount=3 width=500 onmouseout=this.start() onmouseover=this.stop()>MRMS——智能至信，智引未来</marquee>
</h1>
<br>
<br>
<h2 align="center" font-family:\9ED1\4F53>
    会议室:<strong>R01</strong>
</h2>
<br>
<p align="center" line-height="center">
    <video id="video" autoplay="" style='width:640px;height:410px'></video>
    <canvas id="canvas" width="640" height="410"></canvas>
</p>
<br>
<p align="center" line-height="center">
    <button type="button" class="layui-btn layui-btn-warm" id="paizhao" onclick="change()">拍照</button>
    <button type="button" class="layui-btn layui-btn-normal" id="upimg" disabled>签到</button>
</p>

<script type="text/javascript">
    function change() {
        document.getElementById("paizhao").disabled = false;
        document.getElementById("upimg").disabled = false;
    }

    let video = document.getElementById("video");
    let context = canvas.getContext("2d");
    //访问摄像头
    if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
        //调用用户媒体设备, 访问摄像头
        getUserMedia({video: {width: 330, height: 212}}, success, error);
    } else {
        alert('不支持访问用户媒体');
    }

    function getUserMedia(constraints, success, error) {
        if (navigator.mediaDevices.getUserMedia) {
            //最新的标准API
            navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
        } else if (navigator.webkitGetUserMedia) {
            //webkit核心浏览器
            navigator.webkitGetUserMedia(constraints, success, error)
        } else if (navigator.mozGetUserMedia) {
            //firfox浏览器
            navigator.mozGetUserMedia(constraints, success, error);
        } else if (navigator.getUserMedia) {
            //旧版API
            navigator.getUserMedia(constraints, success, error);
        }
    }

    //成功回调
    function success(stream) {

        //兼容webkit核心浏览器
        var CompatibleURL = window.URL || window.webkitURL;

        //将视频流设置为video元素的源
        video.srcObject = stream;
        video.play();
    }

    //失败回调
    function error(error) {
        console.log("访问用户媒体设备失败");
    }

    document.getElementById("paizhao").addEventListener("click", function () {
        context.drawImage(video, 0, 0, 640, 410);
    });

    function UploadPic() {
        var Pic = document.getElementById("canvas").toDataURL("image/jpg");
        Pic = Pic.replace(/^data:image\/(png|jpg);base64,/, "");

        $.ajax({
            url: "checkIn.action?rid=R01",
            type: "post",
            datatype: "json",
            data: {"image": Pic},

            success: function (data) {
                //传入成功时的操作
                var json = JSON.stringify(data);
                var a = eval('(' + json + ')');
                alert(a.out);
            },
            error: function (data) {
                //传入失败时的操作
                var json = JSON.stringify(data);
                var a = eval('(' + json + ')');
                alert(a.out);
            }
        });
    }

    document.getElementById("upimg").addEventListener("click", function () {
        UploadPic();
    });
</script>
</body>
</html>