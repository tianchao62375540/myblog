package com.tc.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * @Auther: tianchao
 * @Date: 2020/2/3 10:25
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name="t_blog_tags")
public class BlogTags {

    private Long blogsId;

    private Long tagsId;
}
