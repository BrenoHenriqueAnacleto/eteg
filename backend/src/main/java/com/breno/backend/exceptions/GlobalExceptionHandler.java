package com.breno.backend.exceptions;

import com.breno.backend.presentation.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNoHandlerFoundException(NotFoundException exception) {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .descricao(exception.getMessage())
                .erro(Boolean.TRUE)
                .codigo(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleSQLException(SQLException ex) {
        ApiResponse apiResponse = ApiResponse
                .builder()
                .erro(Boolean.TRUE)
                .codigo(HttpStatus.NOT_FOUND.value())
                .build();

        if (ex.getMessage().contains("duplicate key value violates unique constraint")) {
            apiResponse.setDescricao(formatarMensagemDeErro(ex.getMessage()));
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse.setDescricao("Erro no banco de dados: " + ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    private String formatarMensagemDeErro(String message) {
        Pattern pattern = Pattern.compile("Key \\((.*?)\\)=\\((.*?)\\) already exists");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String campo = matcher.group(1);
            String valor = matcher.group(2);
            return String.format("O %s '%s' já está cadastrado.", campo, valor);
        }

        return "Erro de chave única: valor já cadastrado.";
    }
}
