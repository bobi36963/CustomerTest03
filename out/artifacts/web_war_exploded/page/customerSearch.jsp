<%@ page import="entity.Customer" %>
<%--
  Created by IntelliJ IDEA.
  User: bobo
  Date: 2021/5/15
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>查看详情</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="SearchCustomerServlet" method="post">
                    <div class="layui-form-item">


                        <div class="layui-inline">
                            <label class="layui-form-label">客户姓名<span style="color: red">*</span></label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input"
                                       id="searchCustomerName" name="searchCustomerName"
                                       lay-verify="customerName"
                                       autocomplete="off" placeholder="请输入真实姓名">
                            </div>
                        </div>


                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>

                    </div>
                </form>
            </div>
        </fieldset>
    </div>
</div>


<%--信息表单的js--%>
<%!
    String operatingResult = "noOperating";
    String customerId="";
    String customerName="";
    String customerSex="";
    String customerPhone="";
    String customerEmail="";
    String customerRemark="";
%>
<%
    if (request.getParameter("operatingResult") != null) {
        operatingResult = request.getParameter("operatingResult");
    }
%>

<%
    //查看是否查询成功，若成功则弹出提示 成功，并且获取customer对象，否则只弹出提示 失败
    if (operatingResult.equals("searchSuccess")) {
%>
            <script>
                 layer.msg('客户信息查询成功！');
            </script>
<%
    response.setContentType("text/html;charset=UTF-8");
    customerId=request.getParameter("customerId");
    customerName=request.getParameter("customerName");
    customerSex=request.getParameter("customerSex");
    customerPhone=request.getParameter("customerPhone");
    customerEmail=request.getParameter("customerEmail");
    customerRemark=request.getParameter("customerRemark");
    operatingResult = "noOperating";
%>

<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        $(function () {

            var id = '<%=customerId%>';
            var name = '<%=customerName%>';
            var sex = '<%=customerSex%>';
            var phone ='<%=customerPhone%>';
            var email ='<%=customerEmail%>';
            var remark='<%=customerRemark%>';
            document.getElementById("customerId").value = id;
            document.getElementById("customerName").value =name;
            document.getElementById("customerPhone").value = phone;
            document.getElementById("customerEmail").value = email;
            document.getElementById("customerRemark").value = remark;



            if(sex=='男'){
                $("input[name='customerSex'][value='男']").attr("checked",true);
            }
            if(sex=='女'){
                $("input[name='customerSex'][value='女']").attr("checked",true);
            }

        });

        form.render();
    });
</script>

<%
} else if (operatingResult.equals("searchFail")) {
%>
<script>
    layer.msg('未找到该客户！');
</script>
<%
        operatingResult = "noOperating";
    }
%>


<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>客户信息</legend>
            <div class="layui-form layuimini-form">

                <form class="layui-form" action="AddCustomerServlet" method="post">

                    <div class="layui-form-item">
                        <label class="layui-form-label">客户编号</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   id="customerId" name="customerId"
                                   readonly>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">客户名称<span style="color: red">*</span></label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   id="customerName" name="customerName"
                                   readonly>
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="customerSex" value="男" title="男" lay-filter="man">
                            <input type="radio" name="customerSex" value="女" title="女" lay-filter="woman">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">手机<span style="color: red">*</span></label>
                        <div class="layui-input-block">
                            <input type="tel" class="layui-input"
                                   id="customerPhone" name="customerPhone"
                                   readonly>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   id="customerEmail" name="customerEmail"
                                   readonly>
                        </div>
                    </div>


                    <div class="layui-form-item">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                        <textarea class="layui-textarea"
                                  id="customerRemark" name="customerRemark"
                                  readonly>
                        </textarea>
                        </div>
                    </div>
                </form>

            </div>
        </fieldset>
    </div>
</div>

<%--搜索表单的js--%>
<script>
    layui.use(['form', 'layedit', 'layer'], function () {
        var form = layui.form,
            layedit = layui.layedit,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.verify({
            searchCustomerName: function (value) {
                if (/^[\u4e00-\u9fa5]{2,5}$/.test(value) == false) {
                    return '请输入您的真实姓名'
                }
            }
        });

        form.render();
    });
</script>


</body>
</html>
