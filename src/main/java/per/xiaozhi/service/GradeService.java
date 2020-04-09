package per.xiaozhi.service;


import per.xiaozhi.pojo.Grade;

import java.util.List;

/*
 * @description: 业务层-操控年级信息
 * @date: 2019/12/11-16:48
 * @auther: xiaozhi
 *
*/
public interface GradeService {

    // 根据年级名称查询指定/全部年级列表信息
    List<Grade> selectList(String name);

    // 查询所有年级列表信息(用于在班级管理页面中获取年级信息)
    List<Grade> selectAll();

    // 根据年级名称查询指定年级信息
    Grade findByName(String name);

    // 添加年级信息
    int insert(Grade grade);

    // 根据id修改指定年级信息
    int update(Grade grade);

    //根据id删除指定年级信息
    int deleteById(Integer id);
}
