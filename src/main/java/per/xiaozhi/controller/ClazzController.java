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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xiaozhi.pojo.Clazz;
import per.xiaozhi.pojo.Grade;
import per.xiaozhi.service.ClazzService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("clazz")
public class ClazzController {
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    ClazzService clazzService;

    @RequestMapping("/goClazzListView")
    public String goClazzListView(){
        return "clazz/clazzList";
    }


    @RequestMapping("/getAllClazzList")
    @ResponseBody
    public Map<String, Object> getAllClazzList(Integer page, Integer limit) {

        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Clazz> list = clazzService.selectAll();
        //封装查询结果
        PageInfo<Clazz> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Clazz> clazzList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", clazzList);
        return result;
    }

    /**
     * @description: 分页查询:根据班级名和年级获取指定/所有班级信息列表
     * @param: page 当前页码
     * @param: limit 列表行数
     * @param: username 管理员姓名
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @RequestMapping("/getClazzList")
    @ResponseBody
    public Map<String, Object> getClazzList(Integer page, Integer limit, String name,String gradeName) {

        Clazz clazz = new Clazz();
        clazz.setName(name);
        clazz.setGradeName(gradeName);
        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Clazz> list = clazzService.selectList(clazz);
        //封装查询结果
        PageInfo<Clazz> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Clazz> clazzList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", clazzList);
        return result;
    }


    @RequestMapping("/addClazz")
    public String addClazz(String name, Integer number,String teacherName,String telephone,String email, String introduction,String gradeName) {
        Clazz clazz = new Clazz(name,number,introduction,teacherName,telephone,email,gradeName);
        int count = clazzService.insert(clazz);
        //存储对象数据
       if (count>0){
           return "clazz/clazzList";
       }
       return "error/404";
    }

    @RequestMapping("/editClazz")
    public String editClazz(Integer id,String name, Integer number,String teacherName,String telephone,String email, String introduction,String gradeName) {
        Clazz clazz = new Clazz(id,name,number,introduction,teacherName,telephone,email,gradeName);
        int count = clazzService.update(clazz);
        //存储对象数据
        if (count>0){
            return "clazz/clazzList";
        }
        return "error/404";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object>delete(Integer id) {
        int i = clazzService.deleteById(id);
        if (i>0){
            //存储对象数据
            result.put("code",200);
            result.put("msg","删除成功!");
        }
        return result;
    }


    /*
     *Created by IntelliJ IDEA
     * @description:获取不分页的班级列表
     * @date: 2019/12/26-17:34
     * @auther: xiaozhi
     *
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public Map<String, Object> getAll() {
        List<Clazz> list = clazzService.selectAll();
        result.put("msg","查询成功!");
        result.put("data", list);
        return result;
    }



}
