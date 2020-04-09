<%--
  Created by IntelliJ IDEA.
  User: xiaozhi
  Date: 2019/12/17
  Time: 16:20
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- use EL-Expression-->
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生管理|登录页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/loginAndRegister.css" rel="stylesheet">
</head>
<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
    });
    $(function () {
        // 页面初始化生成验证码
        window.onload = createCode('#loginCode');
        // 验证码切换
        $('#loginCode').click(function () {
            createCode('#loginCode');
        });
        // 登陆事件
        $('#loginBtn').click(function () {
            login();
        });

        //监听重置点击事件
        $("#reBtn").click(function () {
            $('#form')[0].reset()
        });

    });
    // 生成验证码
    function createCode(codeID) {
        var code = "";
        // 验证码长度
        var codeLength = 4;
        // 验证码dom元素
        var checkCode = $(codeID);
        // 验证码随机数
        var random = [0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
            'S','T','U','V','W','X','Y','Z'];
        for (var i = 0;i < codeLength; i++){
            // 随机数索引
            var index = Math.floor(Math.random()*36);
            code += random[index];
        }
        // 将生成的随机验证码赋值
        checkCode.val(code);
    }
    // 校验验证码、用户名、密码、用户
    function validateCode(inputID,codeID) {
        var inputCode = $(inputID).val().toUpperCase();
        //生成的验证码
        var cardCode = $(codeID).val();
        //输入的用户名
        var loginUsername = $('#loginUsername').val();
        //表单输入的密码
        var loginPassword = $('#loginPassword').val();
        //选择的用户
        var userType = $('#Select option:selected').val();
        if ($.trim(loginUsername) == '' || $.trim(loginUsername).length<=0){
            layer.alert("用户名不能为空");
            return false;
        }
        if ($.trim(loginPassword) == '' || $.trim(loginPassword).length<=0){
            layer.alert("密码不能为空");
            return false;
        }
        if (inputCode.length<=0){
            layer.alert("验证码不能为空");
            return false;
        }
        if (inputCode != cardCode){
            layer.alert("请输入正确验证码");
            return false;
        }
        if ($.trim(userType)==''||$.trim(userType).length<=0){
            layer.alert("请选择用户角色");
            return false;
        }
        return true;
    }

    // 登录流程
    function login() {
        //判断输入是否正确
        if (!validateCode('#loginCard','#loginCode')){
            //阻断提示
        }else {
            var name = $("#loginUsername").val();
            var password = $("#loginPassword").val();
            var userType = $("#Select option:selected").val();
            console.log(name+" "+userType+" "+password);
            var params = {};
            params.name = name;
            params.password = password;
            params.userType = userType;
            var loginLoadIndex = layer.load(2);
            $('#loginBtn').val("正在登录...");
            $.ajax({
                type:'post',
                url:"${pageContext.request.contextPath}/system/login",
                dataType:'json',
                contentType:'application/json',
                data:JSON.stringify(params),
                success:function (data) {
                    layer.close(loginLoadIndex);
                    if (data.success){
                        window.location.href = "${pageContext.request.contextPath}/system/goSystemMainView";//进入系统主页面
                    }else {
                        alert(data.msg);
                    }
                },
                error:function (data) {
                    layer.close(loginLoadIndex);
                    $('#loginBtn').val("登录");
                    alert(data.msg);

                }
            });
        }
    }
</script>
<body>
<div class="wrap">
    <img src="${pageContext.request.contextPath}/static/images/back.jpg" class="imgStyle">
    <div class="loginForm">
        <form id="form">
            <div class="logoHead">
                <h2>学生管理系统|登录界面</h2>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>用户名:</label>
                </div>
                <div class="usernameDiv">
                    <i class="layui-icon layui-icon-username adminIcon"></i>
                    <input id="loginUsername" class="layui-input adminInput" type="text" name="username" placeholder="输入用户名" >
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>密码:</label>
                </div>
                <div class="passwordDiv">
                    <i class="layui-icon layui-icon-password adminIcon"></i>
                    <input id="loginPassword" class="layui-input adminInput" type="password" name="password" placeholder="输入密码">
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>验证码:</label>
                </div>
                <div class="cardDiv">
                    <input id="loginCard" class="layui-input cardInput" type="text" name="card" placeholder="输入验证码">
                </div>
                <div class="codeDiv">
                    <input id="loginCode" class="layui-input codeInput"  type="button">
                </div>
            </div>

            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>用户:</label>
                </div>
                <div class="passwordDiv">
                    <select id="Select" class="layui-select">
                        <option value="">请选择...</option>
                        <option value="1">管理员</option>
                        <option value="2">教师</option>
                        <option value="3">学生</option>
                    </select>
                </div>
            </div>

            <div class="usernameWrapDiv">
                <div class="submitDiv" style="margin-left: 70px">
                    <input id="reBtn" type="button" class="submit layui-btn layui-btn-primary" value="重置"></input>
                </div>

                <div class="submitDiv">
                    <input id="loginBtn" type="button" class="submit layui-btn layui-btn-primary" value="登录"></input>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
