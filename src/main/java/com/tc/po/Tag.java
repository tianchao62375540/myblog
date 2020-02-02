package com.tc.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 19:55
 * @Description:
 */
@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "t_tag")
public class Tag {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
