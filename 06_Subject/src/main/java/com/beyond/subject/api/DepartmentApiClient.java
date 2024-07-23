package com.beyond.subject.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.beyond.subject.dto.DepartmentResponseDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DepartmentApiClient {
	private final RestTemplate restTemplate;
	@Value("${department-service.base-url}")
	private String baseUrl;
	
	public ResponseEntity<DepartmentResponseDto> getDepartmentByDeptNo(String deptNo) {
		ResponseEntity<DepartmentResponseDto> response = restTemplate.exchange(
			baseUrl + deptNo, 
			HttpMethod.GET,
			null,
			DepartmentResponseDto.class
		);
				
		return response;
	}
}
