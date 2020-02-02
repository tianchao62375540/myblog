package com.tc.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 19:54
 * @Description:
 */
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    /**
     * 分类名字
     */
    @NotBlank(message = "分类名称不能为空1")
    private String name;


    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
}
