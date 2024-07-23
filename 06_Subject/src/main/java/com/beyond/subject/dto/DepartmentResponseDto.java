package com.beyond.subject.dto;

import org.springframework.http.HttpStatus;

import com.beyond.subject.vo.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentResponseDto {
	private int code;

    private String message;

    private Department result;
    
	public DepartmentResponseDto(HttpStatus status, Department department) {
		this.code = status.value();
		this.message = status.getReasonPhrase();
		this.result = department;
	}
}
