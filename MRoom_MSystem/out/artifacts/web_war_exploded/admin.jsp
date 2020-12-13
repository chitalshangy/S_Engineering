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
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">欢迎您，管理员<s:property value="#request.user.uid"/></div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <!--
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a href="javascript:;">会议室管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="">管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="">个人信息管理</a>
            </li>
        </ul>
        -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <!--
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
                -->
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">管理选项</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"><i class="layui-icon layui-icon-user"></i>员工管理</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon layui-icon-chart-screen"></i>会议室管理</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon layui-icon-flag"></i>部门管理</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon layui-icon-picture"></i>人脸库管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div style="padding: 15px;">
            <table id="admin" lay-filter="adminList"></table>
        </div>
    </div>

    <!-- 底部固定区域 -->
    <!--
    <div class="layui-footer">


    </div>
    -->
</div>
<script>
    //JavaScript代码区域
    layui.use(['table','element','form','tree'], function(){
        var table=layui.table;
        var element = layui.element;
        var form=layui.form;
        var tree=layui.tree;

        table.render({
            elem: '#adminList',
            url : 'userAction_findAll.action',
            cellMinWidth : 95,
            page : true,
            height : "full-125",
            limits : [10,15,20,25],
            limit : 10,
            id : "userListTable",
            cols : [[
                {field:'id',tilte:'describtion',width:80,sort:true,fixed:'left'},
                {field:'aid',tilte:'id',width:80,sort:true},
                {field:'apassword',tilte:'密码',width:80,sort:true},
                {field:'aphone',tilte:'联系方式',width:80,sort:true}
            ]]
        });
    });
</script>
</body>
</html>
