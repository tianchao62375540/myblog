package com.tc.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 19:46
 * @Description:
 */
@Data
@NoArgsConstructor
@Entity
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
     * 是否赞赏
     */
    private boolean appreciation;
    /**
     * 是否分享
     */
    private boolean shareStatement;
    /**
     *是否开启评论
     */
    private boolean commentabled;
    /**
     * 是否发布
     */
    private boolean published;
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
