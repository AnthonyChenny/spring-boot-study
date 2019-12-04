/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: jdbcController
 * Author:   Anthony
 * Date:     2019/12/4 10:14
 * Description: jdbc
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.anthony.springboot.controller;

import com.anthony.springboot.bean.Department;
import com.anthony.springboot.bean.Employee;
import com.anthony.springboot.mapper.DepartmentMapper;
import com.anthony.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MybatisController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @ResponseBody
    @GetMapping(value = "/emp/query")
    public Map<String,Object> map() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from employee");
        return maps.get(1);
    }

    //查询一个部门
    @ResponseBody
    @GetMapping(value = "/department/{id}")
    public Department getDepartment(@PathVariable("id")Integer id) {
        return departmentMapper.getDeptById(id);
    }

    //新增一个部门
    @ResponseBody
    @GetMapping(value = "/department")
    public Department addDepartment(Department department) {
        departmentMapper.addDepartment(department);
        return department;
    }

    //下面是使用xml映射文件来操作数据库
    @ResponseBody
    @GetMapping(value = "/employee/{id}")
    public Employee getEmpById(@PathVariable("id")Integer id) {
        return employeeMapper.getEmployee(id);
    }
}

/**
 * 〈一句话功能简述〉<br> 
 * 〈jdbc〉
 *
 * @author Anthony
 * @create 2019/12/4
 * @since 1.0.0
 */