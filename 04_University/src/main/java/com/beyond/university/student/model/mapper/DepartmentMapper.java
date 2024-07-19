package com.beyond.university.student.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.beyond.university.student.model.vo.Department;

@Mapper
public interface DepartmentMapper {
    List<Department> selectAll();

    Department selectDepartmentByDeptNo(@Param("deptNo") String deptNo);

}