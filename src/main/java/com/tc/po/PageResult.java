package com.tc.po;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 12:27
 * @Description:
 */
@Data
public class PageResult<T> {
    private List<T> content;
    private Integer totalPages;
    private Boolean first;
    private Boolean last;
    private Integer number;
    private Long total;

    public static <T> Builder builder(T type){
         return new Builder<T>();
    }

    public static class Builder<T>{
        public PageResult<T> build(PageInfo<T> pageInfo){
            PageResult<T> result = new PageResult();
            result.setContent(pageInfo.getList());
            result.setFirst(pageInfo.isIsFirstPage());
            result.setLast(pageInfo.isIsLastPage());
            result.setTotalPages(pageInfo.getPages());
            result.setNumber(pageInfo.getPageNum());
            result.setTotal(pageInfo.getTotal());
            return result;
        }
    }
}
