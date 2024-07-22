package com.beyond.department.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.beyond.department.vo.Department;

@Mapper
public interface DepartmentMapper {

    int selectDepartmentCount(@Param("openYn") String openYn);

    List<Department> selectDepartments(@Param("openYn") String openYn, RowBounds rowBounds);

    Department selectDepartmentByDeptNo(@Param("deptNo") String deptNo);

    int insertDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(@Param("deptNo") String deptNo);
}