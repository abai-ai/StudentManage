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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import per.xiaozhi.pojo.Grade;
import per.xiaozhi.pojo.Student;
import per.xiaozhi.pojo.Teacher;
import per.xiaozhi.service.StudentService;
import per.xiaozhi.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("teacher")
public class teacherController {
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    TeacherService teacherService;


    @RequestMapping("/goTeacherListView")
    public String goAdminListView(){
        return "teacher/teacherList";
    }


    /*
     *Created by IntelliJ IDEA
     * @description:根据班级与学生名获取学生信息
     * 也可以查询所有
     * @date: 2019/12/26-16:58
     * @auther: xiaozhi
     *
    */
    @PostMapping("/getTeacherList")
    @ResponseBody
    public Map<String, Object> getTeacherList(Integer page, Integer limit, String username,String clazz) {

        System.out.println("教师名"+username+"班级名"+clazz);

        //获取查询的用户名
        Teacher teacher = new Teacher();
        teacher.setName(username);
        teacher.setClazzName(clazz);
        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Teacher> list = teacherService.selectList(teacher);
        //封装查询结果
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Teacher> teacherList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", teacherList);
        return result;
    }


    @RequestMapping("/addTeacher")
    public String addTeacher(String tno,String name,String gender,String password, String email, String telephone, String address, String portraitPath,String clazzName) {
        Teacher teacher = new Teacher(tno,name,gender,password,email,telephone,address,portraitPath,clazzName);
        int count = teacherService.insert(teacher);
        //存储对象数据
       if (count>0){
           return "teacher/teacherList";
       }
       return "error/404";
    }

    @RequestMapping("/editTeacher")
    public String editTeacher(Integer id,String tno,String name,String gender,String password, String email, String telephone, String address, String portraitPath,String clazzName) {
        Teacher teacher = new Teacher(id,tno,name,gender,password,email,telephone,address,portraitPath,clazzName);
        int count = teacherService.update(teacher);
        //存储对象数据
        if (count>0){
            return "teacher/teacherList";
        }
        return "error/404";
    }

    //修改密码
    @RequestMapping("/editTeacherPassword")
    public String editTeacherPassword(Integer id, String password,HttpServletRequest request) {
        Teacher teacher = new Teacher();
        teacher.setPassword(password);
        teacher.setId(id);
        int count = teacherService.updatePassowrd(teacher);
        //存储对象数据
        if (count>0){
          request.setAttribute("msg",1);
          return "system/main";
        }
        return "error/404";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object>delete(Integer id) {
        int i = teacherService.deleteById(id);
        if (i>0){
            //存储对象数据
            result.put("code",200);
            result.put("msg","删除成功!");
        }
        return result;
    }

    //图片上传控制器
    @RequestMapping(value = "/uploadFile" , method = RequestMethod.POST)
    @ResponseBody
    public Map uploadPicture(@RequestParam("file")MultipartFile file, HttpServletRequest servletRequest)
            throws IOException {

        Map res = new HashMap();


        //上传文件路径
        String path = servletRequest.getServletContext().getRealPath("/upload");
        System.out.println("文件名称"+file.getOriginalFilename());
        //上传文件名
        String name = file.getOriginalFilename();//上传文件的真实名称

        String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
        // String hash = Integer.toHexString(new Random().nextInt());//自定义随机数（字母+数字）作为文件名
        String hash = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = hash + suffixName;
        File filepath = new File(path, fileName);
        System.out.println("随机数文件名称"+filepath.getName());
        //判断路径是否存在，没有就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文档中
        File tempFile = new File(path + File.separator + fileName);
        file.transferTo(tempFile);
        // resUrl.put("src", tempFile.getPath());

        res.put("code", "0");
        res.put("msg", "");
        res.put("data", tempFile.getName());
        return res;
    }



}
