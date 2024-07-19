package com.beyond.university.student.model.service;

import java.util.List;

import com.beyond.university.student.model.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.beyond.university.student.model.vo.Department;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper mapper;

    @Override
    public List<Department> getDepartments() {

        return mapper.selectAll();
    }

    @Override
    public Department getDepartmentByDeptNo(String deptNo) {
        return mapper.selectDepartmentByDeptNo(deptNo);
    }
}