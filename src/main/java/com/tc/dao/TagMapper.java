package com.tc.dao;

import com.tc.po.Tag;
import com.tc.po.Type;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 15:00
 * @Description:
 */
public interface TagMapper extends Mapper<Tag>, SelectByIdListMapper<Tag,Long> {
    /**
     * 查询置顶标签
     * @param size
     * @return
     */
    List<Tag> selectTagTop(@Param("size") Integer size);
}
