package com.beyond.department.service;

import com.beyond.department.vo.Department;

import java.util.List;

public interface DepartmentService {

    int getTotalCount(String openYn);

    List<Department> getDepartments(int page, int numOfRows, String openYn);

    Department getDepartmentByDeptNo(String deptNo);

    int save(Department department);

    int delete(String deptNo);
}
