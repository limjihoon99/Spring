package com.beyond.subject.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.beyond.subject.vo.Subject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SubjectsResponseDto {
    @Schema(description = "응답 코드", example = "200")
    private int code;

    @Schema(description = "응답 메시지", example = "OK")
    private String message;

    @Schema(description = "응답 데이터")
    private List<Subject> result;
    
    @Schema(description = "페이지 번호", example = "1")
    private int page;

    @Schema(description = "한 페이지 결과 수", example = "10")
    private int numOfRows;
    
    @Schema(description = "전체 결과 수", example = "100")
    private int totalCount;

	public SubjectsResponseDto(HttpStatus status, List<Subject> subjects, int page, int totalCount) {
		this.code = status.value();
		this.message = status.getReasonPhrase();
		this.result = subjects;
		this.page = page;
		this.numOfRows = subjects.size();
		this.totalCount = totalCount;
	}

}
