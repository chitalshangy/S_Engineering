<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Chital
  Date: 2020/12/11
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员界面</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">欢迎您，管理员</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="images/example.jpg" class="layui-nav-img">
                    <s:property value="#request.id"/>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:show_inf(this);">基本资料</a></dd>
                    <dd><a href="null">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="login.jsp">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">管理选项</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="1" data-title="员工管理" data-url="A_staffInfo.jsp"
                               class="site-demo-active" data-type="tabAdd"><i class="layui-icon layui-icon-user"></i>员工管理</a>
                        </dd>
                        <dd><a href="javascript:;" data-id="2" data-title="会议室管理" data-url="A_roomInfo.jsp"
                               class="site-demo-active" data-type="tabAdd"><i
                                class="layui-icon layui-icon-chart-screen"></i>会议室管理</a></dd>
                        <dd><a href="javascript:;" data-id="3" data-title="预约管理" data-url="A_reserveInfo.jsp"
                               class="site-demo-active" data-type="tabAdd"><i class="layui-icon layui-icon-flag"></i>预约管理</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <input type="button" id="parentID" value="" hidden/>


    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
            <ul class="layui-tab-title">
            </ul>
            <div class="layui-tab-content" style="height: 150px;">
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

<!--修改信息的弹窗-->
<div class="site-text" style="margin: 5%; display: none" id="box1" target="123">
    <form class="layui-form layui-form-pane" onsubmit="return false" id="booktype">
        <div class="layui-form-item">
            <label class="layui-form-label"> 序号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="aid" name="aid" value="<%=request.getAttribute("id")%>"
                       readonly><br>
            </div>
            <label class="layui-form-label"> 密码</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="apassword" name="apassword"
                       value="<%=request.getAttribute("password")%>"><br>
            </div>
            <label class="layui-form-label"> 联系方式</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="aphone" name="aphone"><br>
            </div>

        </div>
    </form>
</div>

<script>
    //个人信息弹出框
    function show_inf(t) {
        var $ = layui.$;
        //页面层
        layer.open({
            type: 1
            , skin: 'layui-layer-molv'
            , area: ['380px', '270px']
            , title: ['个人信息', 'font-size:18px']
            , btn: ['修改', '取消']
            , shadeClose: true
            , shade: 0
            , maxmin: true
            , content: $('#box1')
            , success: function (layero, index) {

            }, yes: function (index, layero) {
                $.getJSON('Adminupdate.action', {
                    apassword: $('#apassword').val(),
                    aphone: $('#aphone').val(),
                    aid: $('#aid').val()
                });
                layer.close(index);//关闭弹窗
            }
        });
    }

    layui.use(['element', 'layer', 'jquery'], function () {
        var $ = layui.$;
        var element = layui.element;
        // var layer = layui.layer;
        // 配置tab实践在下面无法获取到菜单元素
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));

                <!--解决过滤器问题-->
                <%
                session.setAttribute("user_id",1);
                %>

            } else {
                //否则判断该tab项是否以及存在
                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));

                    <%
                    session.setAttribute("user_id",1);
                    %>

                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('demo', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("demo", id);//删除
            }
        };

        function FrameWH() {
            var h = $(window).height();
            $("iframe").css("height", h + "px");
        }
    });
</script>
</body>
</html>
