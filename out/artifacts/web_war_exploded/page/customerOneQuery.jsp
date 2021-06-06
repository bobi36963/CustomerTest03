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
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">

            <form class="layui-form" action="AddCustomerServlet" method="post">

                <div class="layui-form-item">
                    <label class="layui-form-label">客户编号</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input"
                               id="customerId" name="customerId"
                               disabled>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">客户名称<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input"
                               id="customerName" name="customerName"
                               lay-verify="customerName"
                               autocomplete="off" placeholder="请输入真实姓名"
                               disabled>
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="customerSex" value="男" title="男" checked lay-filter="man" disabled>
                        <input type="radio" name="customerSex" value="女" title="女" lay-filter="woman" disabled>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">手机<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="tel" class="layui-input"
                               id="customerPhone" name="customerPhone"
                               lay-verify="required|phone" lay-reqtext="请输入正确的电话号码"
                               placeholder="请输入手机号码" autocomplete="off"
                               disabled>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input"
                               id="customerEmail" name="customerEmail"
                               lay-verify="customerEmail" lay-reqtext="请输入正确的邮箱地址"
                               placeholder="请输入邮箱地址" autocomplete="off"
                               disabled>
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入备注信息" class="layui-textarea"
                                  id="customerRemark" name="customerRemark"
                                  disabled></textarea>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        $(function () {
            //从父层获取值，json是父层的全局js变量。eval是将该string类型的json串变为标准的json串
            var json = eval('(' + parent.json + ')');

            document.getElementById("customerName").value = json.name;
            document.getElementById("customerPhone").value = json.phone;
            document.getElementById("customerEmail").value = json.email;
            document.getElementById("customerRemark").value = json.description;

            if(json.gender=='男'){
                $("input[name='customerSex'][value='男']").attr("checked",true);
            }
            if(json.gender=='女'){
                $("input[name='customerSex'][value='女']").attr("checked",true);
            }

        });

        form.render();
    });
</script>
</body>
</html>
