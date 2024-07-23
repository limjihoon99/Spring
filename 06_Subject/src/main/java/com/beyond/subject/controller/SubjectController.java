package com.beyond.subject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.subject.api.DepartmentApiClient;
import com.beyond.subject.dto.DepartmentResponseDto;
import com.beyond.subject.dto.SubjectRequestDto;
import com.beyond.subject.dto.SubjectResponseDto;
import com.beyond.subject.dto.SubjectsResponseDto;
import com.beyond.subject.service.SubjectService;
import com.beyond.subject.vo.Subject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/*
	과목 서비스(subject-service)
	1) 과목 조회
	  - GET /v1/subject-service/subjects
	  - GET /v1/subject-service/subjects/{subject-no}
	2) 과목 등록
	  - POST /v1/subject-service/subjects
	3) 과목 수정
	  - PUT /v1/subject-service/subjects/{subject-no}
	4) 과목 삭제
	  - DELETE /v1/subject-service/subjects/{subject-no}
	5) 과목의 학과 정보 조회
	  - GET /v1/subject-service/subjects/{subject-no}/departments/{department-no}
*/

@RestController
@RequestMapping("/v1/subject-service/subjects")
@RequiredArgsConstructor
@Tag(name = "Subject APIs", description = "과목 관련 API 목록")
public class SubjectController {
    private final SubjectService subjectService;
    private final DepartmentApiClient departmentApiClient;

	@GetMapping("/")
	@Operation(summary = "과목 목록 조회", description = "전체 과목의 목록을 조회한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),   
        @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @Parameters({
        @Parameter(name = "page", description = "페이지 번호", example = "1"),
        @Parameter(name = "numOfRows", description = "한 페이지 결과 수", example = "10")
    })
	public ResponseEntity<SubjectsResponseDto> getSubjects(@RequestParam int page, @RequestParam int numOfRows) {
		int totalCount = subjectService.getTotalCount();
        List<Subject> subjects = subjectService.getSubjects(page, numOfRows, totalCount);

        if (subjects.size() > 0) {        	
        	return ResponseEntity.ok(new SubjectsResponseDto(HttpStatus.OK, subjects, page, totalCount));
        } else {
        	return ResponseEntity.ok(new SubjectsResponseDto(HttpStatus.NOT_FOUND, subjects, page, totalCount));
        }
	}
	
	@GetMapping("/{subject-no}")
    @Operation(summary = "과목 상세 조회", description = "과목 번호로 과목의 상세 정보를 조회한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @Parameter(name = "subject-no", description = "과목 번호", example = "C0003400")
	public ResponseEntity<SubjectResponseDto> getSubjectByNo(@PathVariable("subject-no") String subjectNo) {
        Subject subject = subjectService.getSubjectBySubjectNo(subjectNo);

        if (subject != null) {
            return ResponseEntity.ok(new SubjectResponseDto(HttpStatus.OK, subject));
        } else {
        	return ResponseEntity.ok(new SubjectResponseDto(HttpStatus.NOT_FOUND, subject));
        }
	}
    
	@PostMapping("/")
    @Operation(summary = "과목 등록", description = "과목 정보를 JSON으로 받아서 등록한다.")
    @ApiResponse(responseCode = "201", description = "CREATE", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	public ResponseEntity<SubjectResponseDto> createSubject(@RequestBody SubjectRequestDto requestDto) {
        Subject subject = new Subject(requestDto);
        
        subjectService.save(subject);

        return ResponseEntity.ok(new SubjectResponseDto(HttpStatus.CREATED, subject));
	}
	
	@PutMapping("/{subject-no}")
    @Operation(summary = "과목 정보 수정",description = "과목 정보를 JSON으로 받아 수정한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
	@Parameter(name = "subject-no", description = "과목 번호", example = "C0003400")
	public ResponseEntity<SubjectResponseDto> udpateSubject(
			@PathVariable("subject-no") String subjectNo,
			@RequestBody SubjectRequestDto requestDto) {
        Subject subject = subjectService.getSubjectBySubjectNo(subjectNo);

        if (subject != null) {
        	subject.setRequestDto(requestDto);
			
        	subjectService.save(subject);
			
        	subject = subjectService.getSubjectBySubjectNo(subject.getNo());
			
			return ResponseEntity.ok(new SubjectResponseDto(HttpStatus.OK, subject));
        } else {
        	return ResponseEntity.ok(new SubjectResponseDto(HttpStatus.NOT_FOUND, subject));
        }
	}
	
	@DeleteMapping("/{subject-no}")
    @Operation(summary = "과목 삭제", description = "과목 번호로 해당 과목를 삭제한다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
	@Parameter(name = "subject-no", description = "과목 번호", example = "C0003400")
	public ResponseEntity<SubjectResponseDto> deleteSubject(@PathVariable("subject-no") String subjectNo) {
		Subject subject = subjectService.getSubjectBySubjectNo(subjectNo);
		
		if(subject != null) {			
			subjectService.delete(subject.getNo());
			
//			return ResponseEntity.noContent().build();
			return ResponseEntity.ok(new SubjectResponseDto(HttpStatus.OK, subject));
		} else {
			return ResponseEntity.ok(new SubjectResponseDto(HttpStatus.NOT_FOUND, subject));
		}
	}
	
	@GetMapping("/{subject-no}/departments/{department-no}")
	public ResponseEntity<DepartmentResponseDto> getDepartmentBydeptNo(
			@PathVariable("subject-no") String subjectNo,
			@PathVariable("department-no") String deptNo) {	
		
		Subject subject = subjectService.getSubjectBySubjectNo(subjectNo);
		
		if (subject != null && subject.getDeptNo().equals(deptNo)) {
			return departmentApiClient.getDepartmentByDeptNo(deptNo);
		} else {
			return ResponseEntity.ok(new DepartmentResponseDto(HttpStatus.NOT_FOUND, null));
		}
	}
}
