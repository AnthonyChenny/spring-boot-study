/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Employee
 * Author:   Anthony
 * Date:     2019/12/4 15:52
 * Description: 员工类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anthony.springboot.bean;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer did;
}

/**
 * 〈一句话功能简述〉<br> 
 * 〈员工类〉
 *
 * @author Anthony
 * @create 2019/12/4
 * @since 1.0.0
 */