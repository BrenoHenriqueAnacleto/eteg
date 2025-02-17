package com.breno.backend.presentation;

import com.breno.backend.presentation.request.UsuarioRequest;
import com.breno.backend.presentation.response.ApiResponse;
import com.breno.backend.service.CadastroService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cadastro")
@RequiredArgsConstructor
@Slf4j
public class CadastroController {

    private final CadastroService cadastroService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<ApiResponse> save (
        @Validated @RequestBody UsuarioRequest usuarioRequest
    ) {
        log.info("[INFO] - Iniciando o processo de cadastro de usuario");
        cadastroService.save(usuarioRequest);
        log.info("[INFO] - Cadastro realizado com sucesso");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
