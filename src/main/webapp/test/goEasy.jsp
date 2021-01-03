<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <script type="text/javascript" src="${path}/bootstrap/js/goeasy-1.2.0.js"></script>
        <%--<script type="text/javascript" src="${path}/bootstrap/js/goeasy-1.2.0.js"></script>--%>
        <script>

            /*初始化GoEasy对象*/
            var goEasy = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-bb0ba4338a924943b0e7a6a5862d936a", //替换为您的应用appkey
            });

            /*接收消息*/
            goEasy.subscribe({
                channel: "my2006_channel",//替换为您自己的channel
                onMessage: function (message) {  //获取的消息
                    alert("Channel:" + message.channel + " content:" + message.content);
                },
                onSuccess: function () {
                    console.log("Channel订阅成功。");
                },
                onFailed: function (error) {
                    console.log("Channel订阅失败, 错误编码：" + error.code + " 错误信息：" + error.content)
                }
            });


        </script>
    </head>
    <body>
        <h1>lalala</h1>
    </body>
</html>
