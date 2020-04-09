package per.xiaozhi.mapper;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/19-23:25
 *
 */

import per.xiaozhi.pojo.Admin;
import per.xiaozhi.pojo.LoginForm;

import java.util.List;

public interface AdminMapper {
    // 验证登录信息是否正确
    Admin login(LoginForm loginForm);
    // 通过姓名查询指定管理员信息
    Admin findByName(String name);
    // 添加管理员信息
    int insert(Admin admin);
    // 根据姓名查询指定/所有管理员信息列表
    List<Admin> selectList(Admin admin);
    // 根据id更新指定管理员信息
    int update(Admin admin);
    //  根据id修改指定管理员密码
    int updatePassword(Admin admin);
    // 根据id删除指定管理员信息
    int deleteById(Integer id);
}
