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
        $("#AdminTable").jqGrid({
            url :"${path}/admin/queryAdmin",  //接收  page=当前页   rows=每页展示条数   返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
            editurl:"${path}/admin/edit", //增删改走的路线 oper:add ,edit,del
            datatype : "json", //数据格式
            rowNum : 2, //每页展示条数
            rowList : [ 2,10,20,30 ], //可选展示条数
            pager : 'adminPage',  //分页工具栏
            sortname : 'id',    //排序
            type:"post",    //请求方式
            styleUI:"Bootstrap", //使用Bootstrap
            autowidth:true, //宽度自动
            height:"auto",  //高度自动
            viewrecords:true, //是否展示总条数
            colNames : [ 'Id', '用户名', '密码', '状态', '加密' ],
            colModel : [
                {name : 'id',index : 'id',width : 55,align : "center"},
                {name : 'name',editable:true ,index : 'invdate',width : 90,align : "center"},
                {name : 'password',editable:true,index : 'name asc, invdate',width : 100,align : "center"},
                {name : 'struts',index : 'amount',width : 80,align : "center",edittype:"file"},
                {name : 'salt',index : 'tax',width : 80,align : "center"}
            ]
        });
        //分页工具栏
        $("#AdminTable").jqGrid('navGrid', '#adminPage',
            {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑",deltext:"删除"},
            {
                closeAfterEdit:true,  //关闭对话框
            },  //修改之后的额外操作
            {
                closeAfterAdd:true,  //关闭对话框
                afterSubmit:function(data){  //提交之后执行
                    //异步文件上传
                    $.ajaxFileUpload({
                        url: "${path}/admin/uploadUserCover", //后台文件上传方法的路径
                        type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                        //dataType: 'json',   //服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
                        fileElementId: "headImg",    //需要上传的文件域的ID，即<input type="file" name="headImg" id="headImg" >的ID。
                        data:{"id":data.responseText},
                        success:function(data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                            //刷新表单
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return "ok";
                }
            },
            //添加之后的额外操作
            {}  //删除之后的额外操作
        );
    }


</script>
<%--创建一个面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <span>管理员信息</span>
    </div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">管理员信息</a></li>
    </ul><br>

    <div>
        <button class="btn btn-info">导出管理员信息</button>
        <button class="btn btn-info">测试</button>
    </div><br>
    <%--创建表格--%>
    <table id="AdminTable" />

    <%--分页工具栏--%>
    <div id="adminPage"/>

</div>