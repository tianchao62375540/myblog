package com.tc.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 19:46
 * @Description:
 */
@Data
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name="t_blog")
public class Blog {
    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 首图
     */
    private String firstPicture;
    /**
     * 标记
     */
    private String flag;
    /**
     * 浏览次数
     */
    private Integer views;
    /**
     * 博客描述
     */
    private String description;
    /**
     * 是否赞赏
     */
    private Boolean appreciation;
    /**
     * 是否分享
     */
    private Boolean shareStatement;
    /**
     *是否开启评论
     */
    private Boolean commentabled;
    /**
     * 是否发布
     */
    private Boolean published;
    /**
     * 是否推荐
     */
    private Boolean recommend;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /**
     * 分类id
     */
    private Long typeId;
    /**
     * 用户id
     */
    private Long userId;

    public void init(){
        this.tagIds = tagsToIds(tags);
    }
    private String tagsToIds(List<Tag> tags){
        if (tags==null||tags.isEmpty()){
            return null;
        }
        return StringUtils.join(tags.stream().map(Tag::getId).collect(Collectors.toList()), ",");
    }

    /**
     * 标签id集合
     */
    @Transient
    private String tagIds;

    /**
     *
     */
    //@ManyToOne
    @Transient
    private Type type;
    /**
     * 级联新增，新增博客的时候 如果有标签，也会新增这些标签
     */
    //@ManyToMany(cascade = {CascadeType.PERSIST})
    @Transient
    private List<Tag> tags = new ArrayList<>();

    //@ManyToOne
    @Transient
    private User user;

    //@OneToMany(mappedBy = "blog")
    @Transient
    private List<Comment> comments = new ArrayList<>();
}
