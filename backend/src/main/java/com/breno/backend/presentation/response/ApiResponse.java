package com.breno.backend.presentation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ApiResponse {
    private String descricao;
    private int codigo;
    private Boolean erro;


    public static ApiResponse ok() {
        return ApiResponse
                .builder()
                .descricao(HttpStatus.OK.getReasonPhrase())
                .codigo(HttpStatus.OK.value())
                .erro(HttpStatus.OK.isError())
                .build();
    }
}
