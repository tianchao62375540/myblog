package com.tc.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 19:56
 * @Description:
 */
@Table(name = "t_comment")
@Data
@NoArgsConstructor
@ToString
@Entity
public class Comment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    /**
     * 昵称
     */
    private String nickname;

    private String email;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 头像
     */
    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;
}
