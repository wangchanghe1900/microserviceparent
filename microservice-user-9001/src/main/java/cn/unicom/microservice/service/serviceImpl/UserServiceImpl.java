package cn.unicom.microservice.service.serviceImpl;

import cn.unicom.microservice.bean.User;
import cn.unicom.microservice.mapper.UserMapper;
import cn.unicom.microservice.service.UserServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 王长河
 * @create 2019-11-19 9:14
 */
@Service
public class UserServiceImpl implements UserServicce {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
}
