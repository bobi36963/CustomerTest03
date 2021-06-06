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
    <title>Title</title>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">

            <form class="layui-form" action="AddCustomerServlet" method="post">

                <div class="layui-form-item">
                    <label class="layui-form-label">客户名称<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input"
                               id="customerName" name="customerName"
                               lay-verify="customerName"
                               autocomplete="off" placeholder="请输入真实姓名">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="customerSex" value="男" title="男"  lay-filter="man">
                        <input type="radio" name="customerSex" value="女" title="女"  lay-filter="woman">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">手机<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="tel" class="layui-input"
                               id="customerPhone" name="customerPhone"
                               lay-verify="required|phone" lay-reqtext="请输入正确的电话号码"
                               placeholder="请输入手机号码" autocomplete="on">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input"
                               name="customerEmail"
                               lay-verify="customerEmail" lay-reqtext="请输入正确的邮箱地址"
                               placeholder="请输入邮箱地址"  autocomplete="on">
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入备注信息" class="layui-textarea"
                                            name="customerRemark"></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="submit" class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">提交信息</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<script>
    layui.use(['form','layedit','layer'], function () {
        var form = layui.form,
            layedit=layui.layedit,
            layer=layui.layer,
            $ = layui.$;

        //焦点监听
        $("#customerName").blur(function(){
            var value = this.value;
            if(typeof value == "undefined" || value == null || value == ""){
                layer.msg("真实姓名为必填项！",{icon:5});
            }else if(/^[\u4e00-\u9fa5]{2,5}$/.test(value)==false){
                layer.msg("请输入真实姓名!",{icon:5});
            }
        })
        $("#customerPhone").blur(function(){
            var value = this.value;
            if(typeof value == "undefined" || value == null || value == ""){
                layer.msg("手机为必填项！",{icon:5});
            }else if(/^1[0-9]{10}$/.test(value)==false){
                layer.msg("请输入正确的手机号码!",{icon:5});
            }
        })


        //监听提交
        form.verify({
            customerName:function (value) {
                if(/^[\u4e00-\u9fa5]{2,5}$/.test(value)==false){
                    return '请输入您的真实姓名'
                }
            },
            customerEmail:function(value){
                if((typeof value != "undefined" && value != null && value != "")&&
                    /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)==false){
                    return '邮箱格式不正确'
                }
            }
        });

        form.render();
    });
</script>

</body>
</html>
