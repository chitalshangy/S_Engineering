<%--
  Created by IntelliJ IDEA.
  User: Chital
  Date: 2020/12/8
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="css/login.css" media="all">
</head>
<body bgcolor="#0F0F0F">
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
                <input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx"/>
                <input type="button" id="new_code" class="login_txtbx" style="background-color: #8D8D8D;text-align: left;" value="点击更换验证码" onclick="createCode()" />
            </div>
        </div>
        <div class="layui-submit larry-login">
            <input type="submit" value="登录" class="submit_btn" onclick="validate()"/>
        </div>
        <div class="layui-submit larry-login">
            <input type="button" value="注册" class="submit_btn" onclick="window.location.href='register.jsp'"/>
        </div>
    </form>
</div>
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
<script type="text/javascript" color="255,255,255" opacity="0.5"count="180">
    ! function() {
        //封装方法，压缩之后减少文件大小
        function get_attribute(node, attr, default_value) {
            return node.getAttribute(attr) || default_value;
        }
        //封装方法，压缩之后减少文件大小
        function get_by_tagname(name) {
            return document.getElementsByTagName(name);
        }
        //获取配置参数
        function get_config_option() {
            var scripts = get_by_tagname("script"),
                script_len = scripts.length,
                script = scripts[script_len - 1]; //当前加载的script
            return {
                l: script_len, //长度，用于生成id用
                z: get_attribute(script, "zIndex", -1), //z-index
                o: get_attribute(script, "opacity", 0.5), //opacity
                c: get_attribute(script, "color", "0,0,0"), //color
                n: get_attribute(script, "count", 99) //count
            };
        }
        //设置canvas的高宽
        function set_canvas_size() {
            canvas_width = the_canvas.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
                canvas_height = the_canvas.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
        }
        //绘制过程
        function draw_canvas() {
            context.clearRect(0, 0, canvas_width, canvas_height);
            //随机的线条和当前位置联合数组
            var e, i, d, x_dist, y_dist, dist; //临时节点
            //遍历处理每一个点
            random_points.forEach(function(r, idx) {
                r.x += r.xa,
                    r.y += r.ya, //移动
                    r.xa *= r.x > canvas_width || r.x < 0 ? -1 : 1,
                    r.ya *= r.y > canvas_height || r.y < 0 ? -1 : 1, //碰到边界，反向反弹
                    context.fillRect(r.x - 0.5, r.y - 0.5, 10, 1); //绘制一个3:1的点
                //从下一个点开始
                for (i = idx + 1; i < all_array.length; i++) {
                    e = all_array[i];
                    // 当前点存在
                    if (null !== e.x && null !== e.y) {
                        x_dist = r.x - e.x; //x轴距离 l
                        y_dist = r.y - e.y; //y轴距离 n
                        dist = x_dist * x_dist + y_dist * y_dist; //总距离, m
                        dist < e.max && (e === current_point && dist >= e.max / 2 && (r.x -= 0.03 * x_dist, r.y -= 0.03 * y_dist), //靠近的时候加速
                            d = (e.max - dist) / e.max,
                            context.beginPath(),
                            context.lineWidth = d * 2,
                            context.strokeStyle = "rgba(" + config.c + "," + (d + 0.2) + ")",
                            context.moveTo(r.x, r.y),
                            context.lineTo(e.x, e.y),
                            context.stroke());
                    }
                }
            }), frame_func(draw_canvas);
        }
        //创建画布，并添加到body中
        var the_canvas = document.createElement("canvas"), //画布
            config = get_config_option(), //配置
            canvas_id = "c_n" + config.l, //canvas id
            context = the_canvas.getContext("2d"), canvas_width, canvas_height,
            frame_func = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(func) {
                window.setTimeout(func, 1000 / 45);
            }, random = Math.random,
            current_point = {
                x: null, //当前鼠标x
                y: null, //当前鼠标y
                max: 20000 // 圈半径的平方
            },
            all_array;
        the_canvas.id = canvas_id;
        the_canvas.style.cssText = "position:fixed;top:0;left:0;z-index:" + config.z + ";opacity:" + config.o;
        get_by_tagname("body")[0].appendChild(the_canvas);

        //初始化画布大小
        set_canvas_size();
        window.onresize = set_canvas_size;
        //当时鼠标位置存储，离开的时候，释放当前位置信息
        /*
        window.onmousemove = function(e) {
            e = e || window.event;
            current_point.x = e.clientX;
            current_point.y = e.clientY;
        }, window.onmouseout = function() {
            current_point.x = null;
            current_point.y = null;
        };
        */
        //随机生成config.n条线位置信息
        for (var random_points = [], i = 0; config.n > i; i++) {
            var x = random() * canvas_width, //随机位置
                y = random() * canvas_height,
                xa = 2 * random() - 1, //随机运动方向
                ya = 2 * random() - 1;
            // 随机点
            random_points.push({
                x: x,
                y: y,
                xa: xa,
                ya: ya,
                max: parseInt(Math.random()*(30000-20000+1)+20000,10) //沾附距离
            });
        }
        all_array = random_points.concat([current_point]);
        //0.1秒后绘制
        setTimeout(function() {
            draw_canvas();
        }, 100);
    }();
</script>
</body>
</html>
