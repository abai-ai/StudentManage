package per.xiaozhi.service.Impl;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/23-10:52
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.xiaozhi.mapper.ClazzMapper;
import per.xiaozhi.pojo.Clazz;
import per.xiaozhi.service.ClazzService;

import java.util.List;
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;
    @Override
    public List<Clazz> selectList(Clazz clazz) {
        return clazzMapper.selectList(clazz);
    }

    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }

    @Override
    public int insert(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    @Override
    public int deleteById(Integer id) {
        return clazzMapper.deleteById(id);
    }

    @Override
    public Clazz findByName(String name) {
        return clazzMapper.findByName(name);
    }

    @Override
    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }
}
