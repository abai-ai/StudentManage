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
import per.xiaozhi.pojo.Admin;
import per.xiaozhi.pojo.Student;
import per.xiaozhi.service.AdminService;
import per.xiaozhi.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("student")
public class StudentController {
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    StudentService studentService;


    @RequestMapping("/goStudentListView")
    public String goAdminListView(){
        return "student/studentList";
    }


    /*
     *Created by IntelliJ IDEA
     * @description:根据班级与学生名获取学生信息
     * @date: 2019/12/26-16:58
     * @auther: xiaozhi
     *
    */
    @PostMapping("/getStudentList")
    @ResponseBody
    public Map<String, Object> getStudentList(Integer page, Integer limit, String username,String clazz) {

        //获取查询的用户名
        Student student = new Student();
        student.setName(username);
        student.setClazzName(clazz);
        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Student> list = studentService.selectList(student);
        //封装查询结果
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Student> studentList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", studentList);
        return result;
    }


    @RequestMapping("/addStudent")
    public String addStudent(String sno,String name, String gender, String password,String email,String telephone,String address,String portraitPath,String introduction,String clazzName) {
        System.out.println(name+gender+portraitPath+clazzName);
        Student student = new Student(sno,name,gender,password,email,telephone,address,introduction,portraitPath,clazzName);
        int count = studentService.insert(student);
        //存储对象数据
        if (count>0){
            return "student/studentList";
        }
        return "error/404";
    }

    @RequestMapping("/editStudent")
    public String editStudent(Integer id,String sno,String name, String gender, String password,String email,String telephone,String address,String portraitPath,String introduction,String clazzName) {
        System.out.println(name+gender+portraitPath+clazzName+sno);
        Student student = new Student(id,sno,name,gender,password,email,telephone,address,introduction,portraitPath,clazzName);
        int count = studentService.update(student);
        //存储对象数据
        if (count>0){
            return "student/studentList";
        }
        return "error/404";
    }

    //修改密码
    @RequestMapping("/editStudentPassword")
    public String editStudentPassword(Integer id, String password,HttpServletRequest request) {
       Student student = new Student();
        student.setPassword(password);
        student.setId(id);
        int count = studentService.updatePassowrd(student);
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
        int i = studentService.deleteById(id);
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
