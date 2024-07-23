package com.beyond.subject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectRequestDto {
	@Schema(description = "학과 번호", example = "001")
	private String deptNo;
	
	@Schema(description = "선수 과목 번호", example = "C0089400")
	private String preattendingNo;
	
	@Schema(description = "과목 이름", example = "문학의 이해")
	private String name;
	
	@Schema(description = "구분", example = "전공 필수")
	private String type;
}
