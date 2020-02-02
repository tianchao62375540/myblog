package com.tc.po;

import lombok.AllArgsConstructor;
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
 * @Date: 2020/1/1 20:00
 * @Description:
 */
@Entity
@Data
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String nickname;

    private String username;

    private String password;

    private String email;

    private String avatar;

    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /**
     *被维护端
     */
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

}
