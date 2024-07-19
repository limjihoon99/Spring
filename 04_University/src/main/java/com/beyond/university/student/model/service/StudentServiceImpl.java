package com.beyond.university.student.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beyond.university.student.model.mapper.StudentMapper;
import com.beyond.university.student.model.vo.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    @Override
    public List<Student> getStudentsByDeptNo(String deptNo) {

        return studentMapper.selectAllByDeptNo(deptNo);
    }

    @Override
    public Student getStudentByNo(String sno) {

        return studentMapper.selectStudentByNo(sno);
    }

    @Override
    public int save(Student student) {

        int result = 0;

        if(student.getNo() != null) {
            // update
            result = studentMapper.updateStudent(student);
        } else {
            // insert
            result = studentMapper.insertStudent(student);
        }

        return result;
    }

    @Override
    @Transactional
    public int delete(String sno) {

        return studentMapper.deleteStudent(sno);
    }
}