package com.tc.service;

import com.github.pagehelper.PageInfo;
import com.tc.po.PageResult;
import com.tc.po.Type;

import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

/**
 * @Auther: tianchao
 * @Date: 2020/1/2 20:11
 * @Description:
 */
public interface TypeService {
    /**
     * 保存分类
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     *
     * @param page
     * @param rows
     * @return
     */
    PageResult<Type> listType(Integer page, Integer rows);

    /**
     * 修改分类
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 删除分类
     * @param id
     */
    void deleteType(Long id);

    Type getTypeByName(String name);

    /**
     * 修改
     * @param id
     * @param type
     * @return
     */
    Type update(Long id, Type type);

    /**
     * 查询全部
     * @return
     */
    List<Type> selectAll();
}
