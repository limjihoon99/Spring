package com.beyond.department.service;

import com.beyond.department.mapper.DepartmentMapper;
import com.beyond.department.vo.Department;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Override
    public int getTotalCount(String openYn) {

        return departmentMapper.selectDepartmentCount(openYn);
    }

    @Override
    public List<Department> getDepartments(int page, int numOfRows, String openYn) {

        /*
        마이바티스에서 페이징 처리

        마이바티스에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공해 준다.
        RowBounds의 객체를 생성할 때 offset과 limit 값을 전달해서 페이징 처리를 쉽게 구현한다.

        offset = 0, limit = 10
          - 0개를 건너뛰고 10개를 가져온다. (1 ~ 10)

        offset = 10, limit = 10
          - 10개를 건너뛰고 10개를 가져온다. (11 ~ 20)

        offset = 20, limit = 10
          - 20개를 건너뛰고 10개를 가져온다. (21 ~ 30)
         */
        int limit = numOfRows;
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        return departmentMapper.selectDepartments(openYn, rowBounds);
    }

    @Override
    public Department getDepartmentByDeptNo(String deptNo) {

        return departmentMapper.selectDepartmentByDeptNo(deptNo);
    }

    @Override
    @Transactional
    public int save(Department department) {

        int result = 0;

        if (department.getNo() != null) {
            result = departmentMapper.updateDepartment(department);
        } else {
            result = departmentMapper.insertDepartment(department);
        }

        return result;
    }

    @Override
    @Transactional
    public int delete(String deptNo) {

        return departmentMapper.deleteDepartment(deptNo);
    }
}
