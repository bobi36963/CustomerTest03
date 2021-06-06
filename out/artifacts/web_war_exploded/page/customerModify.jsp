<%--
  Created by IntelliJ IDEA.
  User: bobo
  Date: 2021/5/24
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>修改</title>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">

            <form class="layui-form" action="ModifyCustomerServlet" method="post">

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
                               lay-verify="customerName"
                               autocomplete="off" placeholder="请输入真实姓名"
                               >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="customerSex" value="男" title="男" lay-filter="man" >
                        <input type="radio" name="customerSex" value="女" title="女" lay-filter="woman" >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">手机<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="tel" class="layui-input"
                               id="customerPhone" name="customerPhone"
                               lay-verify="required|phone" lay-reqtext="请输入正确的电话号码"
                               placeholder="请输入手机号码" autocomplete="off"
                               >
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input"
                               id="customerEmail" name="customerEmail"
                               lay-verify="customerEmail" lay-reqtext="请输入正确的邮箱地址"
                               placeholder="请输入邮箱地址" autocomplete="off"
                               >
                    </div>
                </div>


                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入备注信息" class="layui-textarea"
                                  id="customerRemark" name="customerRemark"
                                  ></textarea>
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
            layer = layui.layer,
            $ = layui.$;

        $(function () {
            //从父层获取值，json是父层的全局js变量。eval是将该string类型的json串变为标准的json串
            var json = eval('(' + parent.json + ')');

            document.getElementById("customerId").value = json.id;
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
