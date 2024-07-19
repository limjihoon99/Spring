package com.beyond.university.student.model.service;

import com.beyond.university.student.model.vo.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments();

    Department getDepartmentByDeptNo(String deptNo);
}
