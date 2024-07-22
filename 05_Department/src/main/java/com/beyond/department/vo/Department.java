package com.beyond.department.vo;

import com.beyond.department.dto.DepartmentRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Schema(description = "학과 번호", example = "001")
    private String no;

    @Schema(description = "학과 이름", example = "국어국문학과")
    private String name;

    @Schema(description = "계열", example = "인문사회")
    private String category;

    @Schema(description = "개설 여부", example = "Y")
    private String openYn;

    @Schema(description = "정원", example = "100")
    private int capacity;

    public Department(DepartmentRequestDto requestDto) {

        this.setDepartmentRequestDto(requestDto);
    }

    public void setDepartmentRequestDto(DepartmentRequestDto requestDto) {

        this.name = requestDto.getName();
        this.category = requestDto.getCategory();
        this.openYn = requestDto.getOpenYn();
        this.capacity = requestDto.getCapacity();
    }
}
