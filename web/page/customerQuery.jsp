<%--
  Created by IntelliJ IDEA.
  User: bobo
  Date: 2021/5/18
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户查询</title>
    <%!
        String operatingResult = "noOperating";
    %>
    <%
        request.setCharacterEncoding("utf-8");
       if(request.getParameter("operatingResult")!=null){
           operatingResult = request.getParameter("operatingResult");
       }
    %>

    <%
        if(operatingResult.equals("addSuccess")){
    %>
    <script>
        layer.msg('客户添加成功！');
    </script>
    <%
        operatingResult = "noOperating";
        }else if(operatingResult.equals("addFail")){
    %>
    <script>
        layer.msg('客户添加失败！');
    </script>
    <%
        operatingResult = "noOperating";
        }else if(operatingResult.equals("modifySuccess")){
    %>
    <script>
        layer.msg('客户修改成功！');
    </script>
    <%
        operatingResult = "noOperating";
         }else if(operatingResult.equals("modifyFail")){
    %>
    <script>
        layer.msg('客户修改失败！');
    </script>
    <%
            operatingResult = "noOperating";
        }
    %>

    <div class="layuimini-container layuimini-page-anim">
        <div class="layuimini-main">
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加用户</button>
                    <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 批量删除
                    </button>
                </div>
            </script>

            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

            <script type="text/html" id="currentTableBar">
                <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="look">查看详情</a>
                <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">修改</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
            </script>

            <script type="text/html" id="checkStatusTable">
                <input type="checkbox" checked lay-skin="switch"
                       lay-text="启用|禁用">
            </script>

            <!--        <script type="text/html" id="usernameTmp">-->
            <!--                &lt;!&ndash; href改为用户详情页面userDetail.html&ndash;&gt;-->
            <!--                <a href="table/userDetail.html" class="layui-table-link">{{d.username}}</a>-->
            <!--         </script>-->

        </div>
    </div>

    <script>

        var json;

        layui.use(['form', 'table', 'miniPage', 'element'], function () {
            var $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                layer = layui.layer,
                miniPage = layui.miniPage;
            form.render();

            table.render({
                elem: '#currentTableId',
                url: 'QueryAllCustomerServlet',
                toolbar: '#toolbarDemo',
                defaultToolbar: ['filter', 'exports', 'print', {
                    title: '提示',
                    layEvent: 'LAYTABLE_TIPS',
                    icon: 'layui-icon-tips'
                }],
                cols: [[
                    {type: "checkbox", width: 120},
                    {field: 'id', width: 100, title: '编号', sort: true},
                    {field: 'name', width: 150, title: '姓名'},
                    {field: 'gender', width: 100, title: '性别'},
                    {field: 'phone', width: 150, title: '手机号码'},
                    {field: 'email', width: 200, title: '邮箱'},
                    {field: 'description', width: 300, title: '描述'},
                    {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
                ]],
                limits: [15,30,45,60,75,90],
                limit: 15,
                page: true,
                skin: 'line'
            });

            /**
             * toolbar监听事件
             */
            table.on('toolbar(currentTableFilter)', function (obj) {
                if (obj.event === 'add') {  // 监听添加操作
                    var content = miniPage.getHrefContent('page/customerAdd.jsp');
                    var openWH = miniPage.getOpenWidthHeight();

                    var index = layer.open({
                        title: '添加用户',
                        type: 1,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: [openWH[0] + 'px', openWH[1] + 'px'],
                        offset: [openWH[2] + 'px', openWH[3] + 'px'],
                        content: content,
                    });
                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                } else if (obj.event === 'delete') {  // 监听删除操作
                    var checkStatus = table.checkStatus('currentTableId')
                        , data = checkStatus.data;
                    if (data.length > 0) {
                        layer.confirm('确定删除选中的行？', {icon: 3, title: '提示信息'}, function (index) {
                            $.ajax({
                                url: 'DeleteMultipleCustomerServlet',
                                data: {
                                    borId: JSON.stringify(data),
                                },
                                method: 'get',
                                contentType: 'application/json',
                                success: function () {
                                    //layui中找到CheckBox所在的行，并遍历找到行的顺序
                                    $("div.layui-table-body table tbody input[name='layTableCheckbox']:checked").each(function () { // 遍历选中的checkbox
                                        n = $(this).parents("tbody tr").index();  // 获取checkbox所在行的顺序
                                        //移除行
                                        $("div.layui-table-body table tbody ").find("tr:eq(" + n + ")").remove();
                                        //如果是全选移除，就将全选CheckBox还原为未选中状态
                                        $("div.layui-table-header table thead div.layui-unselect.layui-form-checkbox").removeClass("layui-form-checked");
                                    });
                                    layer.close(index);
                                    layer.msg('成功删除多个客户！');
                                }
                            });
                        });
                    } else {
                        layer.msg("请选择需要删除的行");
                    }

                }
            });

            //监听表格复选框选择
            table.on('checkbox(currentTableFilter)', function (obj) {
                console.log(obj)
            });

            table.on('tool(currentTableFilter)', function (obj) {
                var data = obj.data;
                if (obj.event === 'edit') {
                    json = JSON.stringify(data);
                    var content = miniPage.getHrefContent('page/customerModify.jsp');
                    var openWH = miniPage.getOpenWidthHeight();

                    var index = layer.open({
                        title: '修改用户',
                        type: 1,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: [openWH[0] + 'px', openWH[1] + 'px'],
                        offset: [openWH[2] + 'px', openWH[3] + 'px'],
                        content: content,
                    });
                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                    return false;
                } else if (obj.event === 'delete') {
                    layer.confirm('确定删除行吗？', function (index) {
                        $.ajax({
                            url: 'DeleteCustomerServlet',
                            data: {
                                borId: JSON.stringify(data),
                            },
                            method: 'get',
                            contentType: 'application/json',
                            success: function () {
                                obj.del();
                                layer.close(index);
                                layer.msg('成功删除客户！');
                            }
                        });
                    });
                } else if (obj.event === 'look') {
                    json = JSON.stringify(data);
                    var content = miniPage.getHrefContent('page/customerOneQuery.jsp');
                    var openWH = miniPage.getOpenWidthHeight();
                    var index = layer.open({
                        title: '查看详情',
                        type: 1,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: [openWH[0] + 'px', openWH[1] + 'px'],
                        offset: [openWH[2] + 'px', openWH[3] + 'px'],
                        content: content,
                    });
                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                    return false;

                }
            });
        });


    </script>

</head>
<body>
</body>
</html>
