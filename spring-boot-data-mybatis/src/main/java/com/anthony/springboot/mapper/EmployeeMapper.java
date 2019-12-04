package com.anthony.springboot.mapper;

import com.anthony.springboot.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
    public Employee getEmployee(Integer id);

    public int addEmployee(Employee employee);
}
