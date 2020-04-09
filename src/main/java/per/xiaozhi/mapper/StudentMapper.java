package per.xiaozhi.mapper;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/19-23:28
 *
 */

import per.xiaozhi.pojo.LoginForm;
import per.xiaozhi.pojo.Student;

import java.util.List;

public interface StudentMapper {

    //  验证登录信息是否正确
    Student login(LoginForm loginForm);

    //  根据班级与学生名获取指定或全部学生信息列表
    List<Student> selectList(Student student);

    //  添加班级信息
    int insert(Student student);

    // 根据id修改指定学生信息
    int update(Student student);

    // 根据id修改指定学生密码
    int updatePassword(Student student);

    // 根据id删除指定学生信息
    int deleteById(Integer id);
}
