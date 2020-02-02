package com.tc.service.impl;

import com.tc.dao.UserMapper;
import com.tc.dao.UserRepository;
import com.tc.po.User;
import com.tc.service.UserService;
import com.tc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 21:06
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    //private UserRepository userRepository;
    /**
     * 检查用户名密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUser(String username, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username)
                .andEqualTo("password", MD5Utils.code(password));
        User user = userMapper.selectOneByExample(example);
        return user;
    }
}
