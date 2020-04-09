package per.xiaozhi.controller;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/23-15:58
 *
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xiaozhi.pojo.Admin;
import per.xiaozhi.pojo.Clazz;
import per.xiaozhi.pojo.Grade;
import per.xiaozhi.service.AdminService;
import per.xiaozhi.service.GradeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("grade")
public class GradeController {
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    GradeService gradeService;


    @RequestMapping("/goGradeListView")
    public String goAdminListView(){
        return "grade/gradeList";
    }


    /*
     *Created by IntelliJ IDEA
     * @description: 根据年级名模糊查询
     * @date: 2019/12/26-15:39
     * @auther: xiaozhi
     *
    */
    @RequestMapping("/getGradeList")
    @ResponseBody
    public Map<String, Object> getGradeList(Integer page, Integer limit, String name) {
        System.out.println(name);

        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Grade> list = gradeService.selectList(name);
        //封装查询结果
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Grade> gradeList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", gradeList);
        return result;
    }

    /*
     *Created by IntelliJ IDEA
     * @description: 获取所有不分页
     * @date: 2019/12/25-22:20
     * @auther: xiaozhi
     *
    */
    @RequestMapping("/getAllGradeList")
    @ResponseBody
    public Map<String, Object> getAllGradeList() {
        List<Grade> list = gradeService.selectAll();
        result.put("msg","查询成功!");
        result.put("data", list);
        return result;
    }

    @RequestMapping("/getAllGradeListWithPage")
    @ResponseBody
    public Map<String, Object> getAllGradeListWithPage(Integer page, Integer limit) {
        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Grade> list = gradeService.selectAll();
        //封装查询结果
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Grade> gradeList = pageInfo.getList();
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", gradeList);
        return result;
    }

    /*
     *Created by IntelliJ IDEA
     * @description: 删除年级
     * @date: 2019/12/26-15:48
     * @auther: xiaozhi
     *
    */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object>delete(Integer id) {
        int i = gradeService.deleteById(id);
        if (i>0){
            //存储对象数据
            result.put("code",200);
            result.put("msg","删除成功!");
        }
        return result;
    }

    @RequestMapping("/addGrade")
    public String addGrade(String name, String manager, String email, String telephone,String  introduction) {
       Grade grade = new Grade(name, manager, email, telephone, introduction);
        int count = gradeService.insert(grade);
        //存储对象数据
        if (count>0){
            return "grade/gradeList";
        }
        return "error/404";
    }

    @RequestMapping("/editGrade")
    public String editGrade(Integer id,String name, String manager,String email, String telephone, String introduction) {
        Grade grade = new Grade(id,name, manager, email, telephone, introduction);

        int count = gradeService.update(grade);
        //存储对象数据
        if (count>0){
            return "grade/gradeList";
        }
        return "error/404";
    }







}
