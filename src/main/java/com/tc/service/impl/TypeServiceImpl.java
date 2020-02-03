package com.tc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.dao.TypeMapper;
import com.tc.dao.TypeRepository;
import com.tc.ex.NotFoundException;
import com.tc.po.PageResult;
import com.tc.po.Type;
import com.tc.service.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/1/2 20:11
 * @Description:
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    //private TypeRepository typeRepository;

    /**
     * 保存分类
     *
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type saveType(Type type) {
        //return typeRepository.save(type);
        typeMapper.insertSelective(type);
        return type;
    }

    /**
     * 根据id获取分类
     *
     * @param id
     * @return
     */
    @Override
    public Type getType(Long id) {
        //return typeRepository.getOne(id);
        return typeMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<Type> listType(Integer page, Integer rows) {
        //分頁
        PageHelper.startPage(page,rows);
        Example example = new Example(Type.class);
        //查詢
        List<Type> list = typeMapper.selectByExample(example);
        PageInfo<Type> info = new PageInfo<>(list);
        return PageResult.builder(Type.class).build(info);
    }

    /**
     * 分页
     *
     * @param pageable
     * @return
     *//*
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }*/

    /**
     * 修改分类
     *
     * @param id
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type dbType = typeMapper.selectByPrimaryKey(id);
        if (dbType == null){
            throw new NotFoundException("id不存在");
        }
        BeanUtils.copyProperties(type,dbType);
        typeMapper.updateByPrimaryKey(dbType);
        return dbType;
    }

    /**
     * 删除分类
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteType(Long id) {
        //typeRepository.deleteById(id);
        typeMapper.deleteByPrimaryKey(id);
    }

    /**
     *
     * @param name typeName
     * @return
     */
    @Override
    public Type getTypeByName(String name) {
        Example example = new Example(Type.class);
        example.createCriteria().andEqualTo("name", name);
        return typeMapper.selectOneByExample(example);
    }

    /**
     * 修改
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public Type update(Long id, Type type) {
        type.setId(id);
        typeMapper.updateByPrimaryKey(type);
        return type;
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Type> selectAll() {
        return typeMapper.selectAll();
    }

    /**
     * 首页置顶分类
     *
     * @param size
     * @return
     */
    @Override
    public List<Type> listTypeTop(int size) {
        return null;
    }
}
