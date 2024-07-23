package com.beyond.subject.vo;

import com.beyond.subject.dto.SubjectRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
	@Schema(description = "과목 번호", example = "001")
    private String no;
	
    @Schema(description = "학과 번호", example = "001")
	private String deptNo;
	
    @Schema(description = "선수 과목 번호", example = "C0003400")
	private String preattendingNo;
	
    @Schema(description = "과목 이름", example = "문학의 이해")
	private String name;
	
    @Schema(description = "구분", example = "전공 필수")
	private String type;

    public Subject(SubjectRequestDto requestDto) {
		this.setRequestDto(requestDto);
	}
	
	public void setRequestDto(SubjectRequestDto requestDto) {
		this.deptNo = requestDto.getDeptNo();
		this.preattendingNo = requestDto.getPreattendingNo();
		this.name = requestDto.getName();
		this.type = requestDto.getType();
	}
}
