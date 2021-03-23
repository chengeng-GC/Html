<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>省市级联查询</title>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" >
        function loadDataAjax(){
            //做ajax请求，使用jquery的$.ajax()
            $.ajax({
                url:"queryProvince",
                dataType:"json",
                success:function (resp) {
                    //删除旧的数据
                    $("#province>option:gt(0)").remove();
                    $.each(resp,function (i,n) {
                        //获取select这个dom对象并添加option标签
                        $("#province").append("<option value='"+n.id+"'>"+ n.name +"</option>");
                    })
                }
            })

        }
        $(function () {
            //$(function())在页面的dom的对象加载成功后执行的函数
            loadDataAjax();
            //绑定事件
            $("#btnload").click(function () {
             loadDataAjax();
            })
            $("#province").change(function () {
                $.post("queryCity", {proid:$("#province>option:selected").val()}, callBack, "json")
            })
        })

        function callBack (resp) {
            $("#city>option:gt(0)").remove();
            $.each(resp,function (i,n) {
                $("#city").append("<option value='"+n.id+"'>"+ n.name +"</option>");
            })
        }
    </script>
</head>
<body>
<p>省市级联查询，使用ajax</p>
<div>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>省份名称</td>
            <td>
                <select id="province">
                    <option value="0">请选择</option>
                </select>
                <input type="button" value="load数据" id="btnload"/>
            </td>

        </tr>
        <tr>
            <td>城市：</td>
            <td>
            <select id="city">
                <option value="0">请选择</option>
            </select>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
