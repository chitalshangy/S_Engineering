<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
    <title>html5调用摄像头实现拍照</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<h1>这是会议室R01</h1>

<p align="center">
    <video id="video" autoplay=""style='width:640px;height:480px'></video>
    <canvas id="canvas" width="640" height="480"></canvas>
</p>
<p align="center">
    <button id="paizhao">拍照</button>
    <button id="upimg">签到</button>
</p>

<script type="text/javascript">
    let video=document.getElementById("video");
    let context=canvas.getContext("2d");
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
    document.getElementById("paizhao").addEventListener("click",function(){
        context.drawImage(video,0,0,640,480);
    });
    function UploadPic() {
        var Pic = document.getElementById("canvas").toDataURL("image/jpg");
        Pic = Pic.replace(/^data:image\/(png|jpg);base64,/, "")

        $.ajax({
			url: "checkIn.action?rid=R01",
			type:"post",		
			datatype:"json",
			data:{"image":Pic,},

			success:function(data){
				//传入成功时的操作

			},
			error:function(jqXHR,textStatus,errorThrown){
				//传入失败时的操作
			}
		});
    }
    document.getElementById("upimg").addEventListener("click",function(){
        UploadPic();
    });
</script>
</body>
</html>