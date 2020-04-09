package per.xiaozhi.service;


import per.xiaozhi.pojo.LoginForm;
import per.xiaozhi.pojo.Teacher;

import java.util.List;

/*
 * @description:业务层-操控教师信息
 * @date: 2019/12/11-16:50
 * @auther: xiaozhi
 *
*/
public interface TeacherService {

    //  验证登录信息是否正确
    Teacher login(LoginForm loginForm);

    //根据教师与班级名查询指定/全部教师列表信息
    List<Teacher> selectList(Teacher teacher);

    // 根据工号查询指定教师信息
    Teacher findByTno(Teacher teacher);

    //  添加教师信息
    int insert(Teacher teacher);

    // 根据id修改指定教师信息
    int update(Teacher teacher);

    //  根据id删除指定教师信息
    int deleteById(Integer id);

    //  根据id修改指定教师密码
    int updatePassowrd(Teacher teacher);

}
