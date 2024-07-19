package com.beyond.university.student.model.service;

import java.util.List;

import com.beyond.university.student.model.vo.Student;

public interface StudentService {

    List<Student> getStudentsByDeptNo(String deptNo);

    Student getStudentByNo(String sno);

    int save(Student student);

    int delete(String sno);
}