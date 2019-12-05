/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: User
 * Author:   Anthony
 * Date:     2019/12/5 9:52
 * Description: 用户
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anthony.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity//告诉jpa这是一个实体类(和数据表映射的类)
@Table(name = "tbl_user")//用来指定和哪个表的数据对应;如果省略默认就是user表
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int id;
    @Column(name = "user_name",length = 50)//和数据表对应的列
    private String username;
    @Column//省略默认email就是列名
    private String email;
}

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户〉
 *
 * @author Anthony
 * @create 2019/12/5
 * @since 1.0.0
 */