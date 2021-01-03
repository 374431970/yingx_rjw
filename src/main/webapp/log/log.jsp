<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>

    //延时加载
    $(function(){
       pageInit();
    });
    //创建表格
    function pageInit(){
        $("#logTable").jqGrid({
            url :"${path}/log/querylog",  //接收  page=当前页   rows=每页展示条数   返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
            datatype : "json", //数据格式
            rowNum : 2, //每页展示条数
            rowList : [ 2,10,20,30 ], //可选展示条数
            pager : 'userPage',  //分页工具栏
            sortname : 'id',    //排序
            type:"post",    //请求方式
            styleUI:"Bootstrap", //使用Bootstrap
            autowidth:true, //宽度自动
            height:"auto",  //高度自动
            viewrecords:true, //是否展示总条数
            colName : ['id','操作人员','操作时间','操作内容','是否操作成功'],
            colModel:[
                { name: 'id' ,width : 55 },
                { name: 'logName' , width : 90 },
                { name: 'logTimes',width : 100 },
                {name : 'logOption',width : 80},
                {name : 'status',width : 150}
            ]
        });
        //分页工具栏
        $("#logTable").jqGrid('navGrid', '#logPage',
            {edit : false,add : false,del : false,addtext:"添加",edittext:"编辑",deltext:"删除"},
            $("#userTable").trigger("reloadGrid")
        );
    }

</script>
<%--创建一个面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <span>日志管理</span>
    </div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">日志管理信息</a></li>
    </ul><br>

    <%--创建表格--%>
    <table id="logTable" />

    <%--分页工具栏--%>
    <div id="logPage"/>

</div>