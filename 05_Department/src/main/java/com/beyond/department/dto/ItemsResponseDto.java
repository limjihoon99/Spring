package com.beyond.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ItemsResponseDto<T> extends BaseResponseDto<T> {

    @Schema(description = "페이지 번호", example = "1")
    private int page;

    @Schema(description = "조회 결과 수", example = "1")
    private int numOfRows;

    @Schema(description = "전체 결과 수", example = "1")
    private int totalCount;

    public ItemsResponseDto(HttpStatus status, T result, int page, int numOfRows, int totalCount) {

        super(status, result);

        this.page = page;
        this.numOfRows = numOfRows;
        this.totalCount = totalCount;
    }
}
