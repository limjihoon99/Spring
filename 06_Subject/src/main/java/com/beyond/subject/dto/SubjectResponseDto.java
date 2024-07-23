package com.beyond.subject.dto;

import org.springframework.http.HttpStatus;

import com.beyond.subject.vo.Subject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SubjectResponseDto {
	@Schema(description = "응답 코드", example = "200")
	private int code;

    @Schema(description = "응답 메시지", example = "OK")
    private String message;

    @Schema(description = "응답 데이터")
    private Subject result;
    
	public SubjectResponseDto(HttpStatus status, Subject subject) {
		this.code = status.value();
		this.message = status.getReasonPhrase();
		this.result = subject;
	}
}
