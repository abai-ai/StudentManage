<!-- use EL-Expression-->
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 引入CSS -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">
</head>
<body>

<div id="head_tool">
    <div class="layui-form-item">
        <div class="layui-input-inline">
                <label class="layui-form-label">年级 :</label>
                <div class="layui-input-block" >
                    <select id="grade" name="grade" lay-filter="grade" style="width: 190px;height:37px; border: 1px solid gold">
                        <option value="">请选择:</option>
                    </select>
                </div>
        </div>
        <div class="layui-input-inline" style="margin-left: 110px">
            <button class="layui-btn" id="search" lay-submit lay-filter="search_btn" style="height: 37px">搜索</button>
        </div>

    </div>
</div>

<!-- 管理员列表信息 -->
<table class="layui-hide" id="clazzList" lay-filter="clazzList"></table>
<!-- 表格操作按钮集 -->
<script type="text/html" id="toolbar">
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del">删除</a>
</script>

<script type="text/html" id="head_toolbar">
    <a class="layui-btn layui-btn-mini" lay-event="add">新增</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="refresh">刷新页面</a>
</script>





<script type="text/javascript">

    $(function () {
        layui.use(['element','table','form','jquery','layer'], function() {
            var $ = layui.jquery;//jQuery
            var form = layui.form;//表单
            var table = layui.table;//表格
            var element = layui.element;//元素
            var layer = layui.layer;//弹出层

            //加载表格
            var tableIns = table.render({
                elem:"#clazzList",
                url:"${pageContext.request.contextPath}/grade/getAllGradeListWithPage",
                height: '400' ,
                toolbar:'#head_toolbar',
                method:'post',
                cols:[[
                    {field: 'id', title: 'ID', width: 50, sort: true},
                    {field: 'name', title: '年级名', width: 100},
                    {field: 'manager', title: '年级主任', width: 90},
                    {field: 'telephone', title: '年级主任电话', width: 150},
                    {field: 'email', title: '年级主任邮箱', width: 150},
                    {field: 'introduction', title: '年级简介', width: 120},
                    {field:'toolbar',title:'操作',width:200,toolbar: '#toolbar'}
                ]],
                id: 'testReload',
                even: true, //隔行背景
                event: true,
                page: true,
                skin:'row',
                limit:6,
                limits:[3,4,5,6]

            });

            //获取年级列表
            $.ajax({
                url: '${pageContext.request.contextPath}/grade/getAllGradeList',
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $.each(data.data, function (index, item) {
                        //value和值
                        $('#grade').append(new Option(item.name, item.name));// 下拉菜单里添加元素
                    });
                    layui.form.render("select");
                }
            })



            //搜索框，重载
            form.on('submit(search_btn)', function (data) {
                //获取选择的年级
                var name = $("#grade option:selected").val();
                console.log(name);
                /**
                 * 数据表格的重载功能
                 */
                table.reload('testReload', {
                    method: 'post',
                    url:"${pageContext.request.contextPath}/grade/getGradeList?name="+name,
                    page: {
                        curr: 1 // 重载后从第一页开始
                    }
                });
                return false;
            });


            //监听head_toolbar工具条
            table.on('toolbar(clazzList)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
                console.log("data:"+data);
                if(layEvent === 'add'){ //添加
                    layer.open({
                        type:1,
                        title:'添加年级信息',
                        content:$("#addDialog"),
                        area: ['1000px', '470px'],
                        offset: 'lt',
                        maxmin: true

                    });
                } else if(layEvent === 'refresh'){ //刷新
                    table.reload('testReload', {
                        method: 'post',
                        url:"${pageContext.request.contextPath}/grade/getAllGradeList",
                        page: {
                            curr: 1 // 重载后从第一页开始
                        }
                    });
                    return false;
                }
            });


            //监听工具条
            table.on('tool(clazzList)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
                console.log("data:"+data);
                if(layEvent === 'del'){ //删除
                    var id = data.id;
                    console.log("id:"+id);
                    layer.confirm('真的删除'+id+'行么', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        //向服务端发送删除指令
                        $.ajax({
                            type:'post',
                            url:"${pageContext.request.contextPath}/grade/delete?id="+id,
                            success:function (data) {
                                if (data.code==200){
                                    layer.msg(data.msg);
                                    tableIns.reload();
                                }

                            }
                        });
                    });
                } else if(layEvent === 'edit') { //编辑
                    var data = obj.data;
                    console.log(data.id);
                    layer.open({
                        type:1,
                        title:'修改年级信息',
                        content:$("#editDialog"),
                        area: ['1000px', '470px'],
                        offset: 'lt',
                        maxmin: true,
                        success : function(layero, index) {
                            $("#editid").val(data.id);
                            $("#editname").val(data.name);
                            $("#editintro").val(data.introduction);
                            $("#editmanager").val(data.manager);
                            $("#edittel").val(data.telephone);
                            $("#editeamil").val(data.email);
                            form.render(); //更新全部
                        },

                    });


                }
            });


        });

    });


</script>



<!-- 修改信息窗口 -->
<div id="editDialog"  style="display:none;">
    <!-- 管理员信息表单 -->
    <form id="editForm" class="layui-form" action="${pageContext.request.contextPath}/grade/editGrade" method="post">
        <input type="hidden" id="editid" name="id">
        <div class="layui-form-item">
            <label class="layui-form-label">年级名</label>
            <div class="layui-input-inline">
                <input type="text" id="editname" name="name" lay-verify="required" lay-reqtext="年级名是必填项，岂能为空？" placeholder="请输入年级名" autocomplete="off" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">年级主任</label>
                <div class="layui-input-inline">
                    <input type="text" id="editmanager" name="manager"  placeholder="请输入年级主任姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">老师电话</label>
                <div class="layui-input-inline">
                    <input type="tel" id="edittel" name="telephone" lay-verify="required|phone" placeholder="请输入年级主任电话" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" id="editeamil" name="email" lay-verify="email" placeholder="请输入年级主任邮箱" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">年级简介</label>
                <div class="layui-input-block">
                    <textarea id="editintro" name="introduction" placeholder="请输入内容" class="layui-textarea" style="width: 60% ;height: 20%"></textarea>
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="submit_add">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>


<!-- 添加信息窗口 -->
<div id="addDialog"  style="display:none;">
    <!-- 管理员信息表单 -->
    <form id="addForm" class="layui-form" action="${pageContext.request.contextPath}/grade/addGrade" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">年级名</label>
            <div class="layui-input-inline">
                <input type="text"  name="name" lay-verify="required" lay-reqtext="年级名是必填项，岂能为空？" placeholder="请输入年级名" autocomplete="off" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">年级主任</label>
                <div class="layui-input-inline">
                    <input type="text"  name="manager"  placeholder="请输入年级主任姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">老师电话</label>
                <div class="layui-input-inline">
                    <input type="tel"  name="telephone" lay-verify="required|phone" placeholder="请输入年级主任电话" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text"  name="email" lay-verify="email" placeholder="请输入年级主任邮箱" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">年级简介</label>
                <div class="layui-input-block">
                    <textarea  name="introduction" placeholder="请输入内容" class="layui-textarea" style="width: 60% ;height: 20%"></textarea>
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="submit_add">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>