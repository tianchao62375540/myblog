package com.tc.dao;

import com.tc.po.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/2/3 22:42
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TypeMapperTest {
    @Autowired
    private TypeMapper typeMapper;

    @Test
    public void test(){
        List<Type> types = typeMapper.selectTypeTop(6);
        types.stream().forEach(System.out::println);
    }
}