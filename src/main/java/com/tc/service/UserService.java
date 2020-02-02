package com.tc.service;

import com.tc.po.User;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 21:05
 * @Description:
 */
public interface UserService {
    /**
     * 检查用户名密码
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);
}
