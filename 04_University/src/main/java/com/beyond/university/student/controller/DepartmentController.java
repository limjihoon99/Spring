package com.beyond.university.student.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beyond.university.student.model.service.DepartmentService;
import com.beyond.university.student.model.service.StudentService;
import com.beyond.university.student.model.vo.Department;
import com.beyond.university.student.model.vo.Student;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final StudentService studentService;

    @GetMapping("/department/search")
    public ModelAndView search(ModelAndView modelAndView, @RequestParam(required = false) String deptNo) {

        List<Department> departments = departmentService.getDepartments();

        Department departmentDetail = departmentService.getDepartmentByDeptNo(deptNo);

        if (deptNo != null) {
            List<Student> students = studentService.getStudentsByDeptNo(deptNo);

            modelAndView.addObject("students", students);
        }

        modelAndView.addObject("departments", departments);
        modelAndView.addObject("departmentDetail", departmentDetail);
        modelAndView.setViewName("department/search");

        return modelAndView;
    }

    @GetMapping("/department/info")
    public ModelAndView info(ModelAndView modelAndView, @RequestParam String sno) {
        Student student = studentService.getStudentByNo(sno);
        List<Department> departments = departmentService.getDepartments();

        System.out.println(student);
        System.out.println(departmentService.getDepartmentByDeptNo(student.getDeptNo()));

        modelAndView.addObject("student", student);
        modelAndView.addObject("departments", departments);
        modelAndView.setViewName("student/info");

        return modelAndView;
    }
}