package com.beyond.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentRequestDto {

    @Schema(description = "학과 이름", example = "일어학과")
    private String name;

    @Schema(description = "계열", example = "인문사회")
    private String category;

    @Schema(description = "개설 여부", example = "Y")
    private String openYn;

    @Schema(description = "정원", example = "50")
    private int capacity;
}
