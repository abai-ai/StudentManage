package per.xiaozhi.service.Impl;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/20-15:42
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.xiaozhi.mapper.AdminMapper;
import per.xiaozhi.pojo.Admin;
import per.xiaozhi.pojo.LoginForm;
import per.xiaozhi.service.AdminService;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(LoginForm loginForm) {
        return adminMapper.login(loginForm);
    }

    @Override
    public Admin findByName(String name) {
        return adminMapper.findByName(name);
    }

    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public List<Admin> selectList(Admin admin) {
        return adminMapper.selectList(admin);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public int updatePassowrd(Admin admin) {
        return adminMapper.updatePassword(admin);
    }

    @Override
    public int deleteById(Integer id) {
        return adminMapper.deleteById(id);
    }
}
