package com.beyond.department.controller;

import com.beyond.department.dto.BaseResponseDto;
import com.beyond.department.dto.DepartmentRequestDto;
import com.beyond.department.dto.ItemsResponseDto;
import com.beyond.department.service.DepartmentService;
import com.beyond.department.vo.Department;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
  1) 학과 조회
    - GET /v1/department-service/departments
    - GET /v1/department-service/departments/{department-no}

  2) 학과 등록
    - POST /v1/department-service/departments

  3) 학과 수정
    - PUT /v1/department-service/departments/{department-no}

  4) 학과 삭제
    - DELETE /v1/department-service/departments/{department-no}
 */
@RestController
@RequestMapping("/v1/department-server")
@Tag(name = "Department APIs", description = "학과 관련 API 목록")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /*
      HttpEntity
        - 스프링 프레임워크에서 제공하는 클래스로 HTTP 요청 또는 응답에 해당하는 HTTP Header와 HTTP Body를 포함하는 클래스이다.

      ResponseEntity
        - HttpEntity를 상속하는 클래스로 HTTP 응답 데이터를 포함하는 클래스이다.
        - 개발자가 직접 HTTP Header, Body, Status를 세팅하여 반환할 수 있다.
    */

//    @GetMapping("/departments")
//    @Operation(summary = "학과 목록 조회", description = "전체 학과의 목록을 조회한다.")
//    public ResponseEntity<Map<String, Object>> getDepartments() {
//
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("name", "홍길동");
//        map.put("age", 32);
//        map.put("hobby", new String[] {"축구", "농구", "야구"});
//
//        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/departments")
    @Operation(summary = "학과 목록 조회", description = "전체 학과의 목록을 조회한다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ItemsResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NOT FOUND",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @Parameters(
            value = {
                    @Parameter(name = "page", description = "페이지 번호", example = "1"),
                    @Parameter(name = "numOfRows", description = "한 페이지의 결과 수", example = "10"),
                    @Parameter(name = "openYn", description = "개설 여부", example = "Y")
            }
    )
    public ResponseEntity<ItemsResponseDto<List<Department>>> getDepartments(@RequestParam int page,
                                                           @RequestParam int numOfRows,
                                                           @RequestParam(required = false) String openYn) {

        int totalCount = departmentService.getTotalCount(openYn);
        List<Department> departments = departmentService.getDepartments(page, numOfRows, openYn);

        if(departments.size() > 0) {
            return ResponseEntity.ok(
                    new ItemsResponseDto<>(HttpStatus.OK, departments, page, departments.size(), totalCount));
        } else {
            return ResponseEntity.ok(
                    new ItemsResponseDto<>(HttpStatus.NOT_FOUND, departments, page, departments.size(), totalCount));
        }
    }

    @GetMapping("/departments/{department-no}")
    @Operation(summary = "학과 상세 조회", description = "학과 번호로 학과의 상세 정보를 조회한다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NOT FOUND",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<BaseResponseDto<Department>> getDepartmentByDeptNo(
            @Parameter(description = "학과 번호", example = "001") @PathVariable("department-no") String deptNo) {

        Department department = departmentService.getDepartmentByDeptNo(deptNo);

        if (department != null) {
            return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, department));
        } else {
            return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.NOT_FOUND, department));
        }
    }

    @PostMapping("/departments")
    @Operation(summary = "학과 등록", description = "학과 정보를 JSON으로 받아서 등록한다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "CREATE",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL SERVER ERROR"
                    )
            }
    )
    public ResponseEntity<BaseResponseDto<Department>> create(@RequestBody DepartmentRequestDto requestDto) {

        Department department = new Department(requestDto);

        departmentService.save(department);

        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.CREATED, department));
    }

    @PutMapping("/departments/{department-no}")
    @Operation(summary = "학과 정보 수정", description = "학과 정보를 JSON으로 받아 수정한다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NOT FOUND",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    public ResponseEntity<BaseResponseDto<Department>> update(
            @PathVariable("department-no") String deptNo,
            @RequestBody DepartmentRequestDto requestDto) {

        Department department = departmentService.getDepartmentByDeptNo(deptNo);

        if(department != null) {

            department.setDepartmentRequestDto(requestDto);

            departmentService.save(department);

            department = departmentService.getDepartmentByDeptNo(deptNo);

            return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, department));
        }else {
            return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.NOT_FOUND, department));
        }
    }

    @DeleteMapping("/departments/{department-no}")
    @Operation(summary = "학과 삭제", description = "학과 번호로 해당 학과를 삭제한다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NOT FOUND",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    public ResponseEntity<BaseResponseDto<Department>> delete(
            @Parameter(description = "학과 번호", example = "001") @PathVariable("department-no") String deptNo) {

        Department department = departmentService.getDepartmentByDeptNo(deptNo);

        if (department != null) {

            departmentService.delete(deptNo);

//            return ResponseEntity.noContent().build();
            return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, department));
        } else {
            return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.NOT_FOUND, department));
        }
    }
}
