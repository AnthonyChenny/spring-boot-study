package com.anthony.springboot.mapper;

import com.anthony.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

@Mapper//指定操作数据库的mapper
public interface DepartmentMapper {

    @Select("select * from anthony_department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from anthony_department where id=#{id}")
    public int deleteById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into anthony_department(department_Name) values (#{departmentName})")
    public int addDepartment(Department department);

    @Update("update anthony_department set department_Name=#{departmentName} where id=#{id}")
    public int updateDepartment(Department department);
}
