package com.tc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.dao.TagMapper;
import com.tc.po.PageResult;
import com.tc.po.Tag;
import com.tc.po.Type;
import com.tc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 14:58
 * @Description:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;


    /**
     * 分页
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<Tag> listTags(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Example example = new Example(Tag.class);
        //查詢
        List<Tag> list = tagMapper.selectByExample(example);
        PageInfo<Tag> info = new PageInfo<>(list);
        return PageResult.builder(Tag.class).build(info);
    }

    /**
     * 添加
     * @param tag
     * @return
     */
    @Override
    public Tag saveType(Tag tag) {
        tagMapper.insertSelective(tag);
        return tag;
    }

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    @Override
    public Tag getTagByName(String name) {
        Example example = new Example(Type.class);
        example.createCriteria().andEqualTo("name", name);
        return tagMapper.selectOneByExample(example);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Tag getTag(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public Tag update(Long id,Tag tag) {
        tag.setId(id);
        tagMapper.updateByPrimaryKey(tag);
        return tag;
    }

    @Override
    public void delete(Long id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }

    /**
     * 查询置顶标签 （前端）
     *
     * @param size
     * @return
     */
    @Override
    public List<Tag> selectTagTop(Integer size) {
        List<Tag> tags = tagMapper.selectTagTop(size);
        return tags;
    }
}
