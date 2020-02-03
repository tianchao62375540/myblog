package com.tc.service;

import com.tc.po.PageResult;
import com.tc.po.Tag;
import com.tc.po.Type;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 14:57
 * @Description:
 */
public interface TagService {
    /**
     * 分页
     * @param page
     * @param rows
     * @return
     */
    PageResult<Tag> listTags(Integer page, Integer rows);

    Tag saveType(Tag tag);

    Tag getTagByName(String name);

    Tag getTag(Long id);

    Tag update(Long id,Tag tag);

    void delete(Long id);

    List<Tag> selectAll();

    /**
     * 查询置顶标签 （前端）
     * @param size
     * @return
     */
    List<Tag> selectTagTop(Integer size);
}
