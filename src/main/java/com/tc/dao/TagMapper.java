package com.tc.dao;

import com.tc.po.Tag;
import com.tc.po.Type;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 15:00
 * @Description:
 */
public interface TagMapper extends Mapper<Tag>, SelectByIdListMapper<Tag,Long> {
}
