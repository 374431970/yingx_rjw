<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <script src="${path}/bootstrap/js/jquery.min.js"></script>
        <script type="text/javascript" src="${path}/bootstrap/js/goeasy-1.2.0.js"></script>
        <script type="text/javascript">

            /*初始化GoEasy对象*/
            var goEasy = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-bb0ba4338a924943b0e7a6a5862d936a", //替换为您的应用appkey
            });

            $(function(){

                var messageContent;

                //接收消息
                goEasy.subscribe({
                    channel: "2006TChatWith",//替换为您自己的channel
                    onMessage: function (message) {

                        //判断该消息是否是自己发的
                        if(messageContent!=message.content){
                            //设置消息的样式
                            var optionStyle="<span style='float: left;background-color: #ccaadd;border-radius: 20px' >"+message.content+"</span><br>";

                            //将自己发送的消息展示在展示区域的右侧  追加消息
                            $("#showMsg").append(optionStyle);
                        }
                    }
                });


                //点击发送按钮触发
                $("#sendMsg").click(function(){
                    //获取输入框的内容
                    var content =$("#msg").val();

                    //给变量赋值
                    messageContent=content;

                    /*发送消息*/
                    goEasy.publish({
                        channel: "2006TChatWith",//替换为您自己的channel
                        message: content,//替换为您想要发送的消息内容
                        onSuccess:function(){

                            //清空输入框
                            $("#msg").val("");

                            //设置消息的样式
                            var optionStyle="<span style='float: right;background-color: #c4e3f3;border-radius: 20px' >"+content+"</span><br>";

                            //将自己发送的消息展示在展示区域的右侧  追加消息
                            $("#showMsg").append(optionStyle);
                        },
                        onFailed: function (error) {
                            console.log("消息发送失败，错误编码："+error.code+" 错误信息："+error.content);
                        }
                    });
                });
            });
        </script>

    </head>
    <body>
        <div align="center">
            <h1>2006聊天群</h1>

            <%--整体窗口--%>
            <div style="width: 600px;height: 700px;border: 3px #2aabd2 solid">
                <%--内容展示区域--%>
                <div id="showMsg" style="width: 594px;height: 600px;border: 3px #cad solid"></div>
                <%--内容编辑发送区域--%>
                <div style="width: 594px;height: 88px;border: 3px greenyellow solid">
                    <%--文本域输入内容--%>
                    <input type="text" id="msg" style="width: 500px;height: 82px;border: 3px #4cae4c solid" ></input>
                    <%--发送按钮内容--%>
                    <button id="sendMsg" style="width: 70px;height: 80px;border: 3px orangered solid" >发送</button>
                </div>
            </div>
        </div>
    </body>
</html>

