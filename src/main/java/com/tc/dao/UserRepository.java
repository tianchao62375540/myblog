package com.tc.dao;

import com.tc.po.User;
//import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 21:08
 * @Description:
 */
@Deprecated
public interface UserRepository /*extends JpaRepository<User,Long>*/ {

    User findByUsernameAndPassword(String username,String password);
}
