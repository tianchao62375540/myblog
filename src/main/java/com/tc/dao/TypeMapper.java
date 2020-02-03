package com.tc.dao;

import com.tc.po.Type;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 11:24
 * @Description:
 */
public interface TypeMapper extends Mapper<Type> {
    /**
     * 指定类型
     * @param size
     * @return
     */
    List<Type> selectTypeTop(@Param("size") Integer size);
}
