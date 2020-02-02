package com.tc.dao;

import com.tc.po.Blog;
import com.tc.po.Type;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 18:02
 * @Description:
 */
public interface BlogMapper extends Mapper<Blog> {


    /**
     * 查询
     * @param condition
     * @return
     */

    /*@Select("<script> SELECT b.id,b.title,b.type_id as typeId,b.recommend,b.update_time as updateTime " +
            "FROM t_blog b " +
            "left join t_type t on b.type_id = t.id " +
            "left join t_user u on b.user_id = u.id " +
            "<where>" +
                "<if test=\"title!=null and title !=''\">" +
                    " and b.title like concat('%',#{title},'%')" +
                "</if>" +
                "<if test=\"typeId!=null\">" +
                    " and b.type_id = #{typeId}" +
                "</if>" +
                "<if test=\"recommend!=null\">" +
                    " and b.recommend = #{recommend}" +
                "</if>" +
            "</where> </script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "typeId",column = "typeId"),
            @Result(property = "recommend",column = "recommend"),
            @Result(property = "updateTime",column = "updateTime"),
            @Result(property = "type",javaType = Type.class,one = @One)
    })*/
    List<Blog> listBlog(Blog condition);
}
