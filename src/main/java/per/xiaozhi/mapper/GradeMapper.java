package per.xiaozhi.mapper;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/19-23:28
 *
 */

import org.apache.ibatis.annotations.Param;
import per.xiaozhi.pojo.Grade;

import java.util.List;

public interface GradeMapper {
    // 根据年级名称查询指定/全部年级信息列表
    List<Grade> selectList(@Param("name") String name);

    // 查询所有年级信息列表(用于在班级管理页面中获取年级信息)
    List<Grade> selectAll();

    //根据年级名称查询指定的年级信息
    Grade findByName(String name);

    //  添加年级信息
    int insert(Grade grade);

    // 根据id修改指定年级信息
    int update(Grade grade);

    // 根据id删除指定年级信息
    int deleteById(Integer id);
}
