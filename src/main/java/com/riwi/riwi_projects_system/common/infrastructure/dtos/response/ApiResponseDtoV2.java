package com.riwi.riwi_projects_system.common.infrastructure.dtos.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class ApiResponseDtoV2<TData> {
    private Integer status;
    private String message;
    private TData data;
}
