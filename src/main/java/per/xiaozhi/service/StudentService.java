package per.xiaozhi.service;


import per.xiaozhi.pojo.LoginForm;
import per.xiaozhi.pojo.Student;

import java.util.List;

/*
 * @description: 业务层-操控学生信息
 * @date: 2019/12/11-16:48
 * @auther: xiaozhi
 *
*/
public interface StudentService {

    //验证登录信息是否正确
    Student login(LoginForm loginForm);

    //根据班级与学生名获取指定或全部学生信息列表
    List<Student> selectList(Student student);


    //添加班级信息
    int insert(Student student);

    // 根据id修改指定学生信息
    int update(Student student);

    // 根据id修改指定学生密码
    int updatePassowrd(Student student);

    // 根据id删除指定学生信息
    int deleteById(Integer id);

}

