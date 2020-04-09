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
import per.xiaozhi.service.AdminService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("admin")
public class AdminController {
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    AdminService adminService;


    @RequestMapping("/goAdminListView")
    public String goAdminListView(){
        return "admin/adminList";
    }


    /**
     * @description: 分页查询:根据管理员姓名获取指定/所有管理员信息列表
     * @param: page 当前页码
     * @param: limit 列表行数
     * @param: username 管理员姓名
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("/getAdminList")
    @ResponseBody
    public Map<String, Object> getAdminList(Integer page, Integer limit, String username) {

        //获取查询的用户名
        Admin admin = new Admin();
        admin.setName(username);
        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Admin> list = adminService.selectList(admin);
        //封装查询结果
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Admin> adminList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", adminList);
        return result;
    }

    @RequestMapping("/addAdmin")
    public String addAdmin(String name, String gender, String password,String email,String telephone,String address,String portraitPath) {
        System.out.println(name+gender+portraitPath);
        Admin admin = new Admin(name,gender,password,email,telephone,address,portraitPath);
        int count = adminService.insert(admin);
        //存储对象数据
       if (count>0){
           return "admin/adminList";
       }
       return "error/404";
    }

    @RequestMapping("/editAdmin")
    public String editAdmin(Integer id,String name, String gender, String email,String telephone,String address,String portraitPath) {
        System.out.println(name+gender+portraitPath);
        Admin admin = new Admin(id,name,gender,email,telephone,address,portraitPath);
        int count = adminService.update(admin);
        //存储对象数据
        if (count>0){
            return "admin/adminList";
        }
        return "error/404";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object>delete(Integer id) {
        int i = adminService.deleteById(id);
        if (i>0){
            //存储对象数据
            result.put("code",200);
            result.put("msg","删除成功!");
        }
        return result;
    }

    //修改密码
    @RequestMapping("/editAdminPassword")
    public String editAdminPassword(Integer id, String password,HttpServletRequest request) {
        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setId(id);
        int count = adminService.updatePassowrd(admin);
        //存储对象数据
        if (count>0){
            request.setAttribute("msg",1);
            return "system/main";
        }
        return "error/404";
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
        //保存的文件名格式hash数据+图片后缀
        String fileName = hash + suffixName;
        File filepath = new File(path, fileName);
        System.out.println("随机数文件名称"+filepath.getName());
        //判断路径是否存在，没有就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文件夹中
        File tempFile = new File(path + File.separator + fileName);
        file.transferTo(tempFile);
        // resUrl.put("src", tempFile.getPath());

        res.put("code", "0");
        res.put("msg", "");
        res.put("data", tempFile.getName());
        return res;
    }


}
