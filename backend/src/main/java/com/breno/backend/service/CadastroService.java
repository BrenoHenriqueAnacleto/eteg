package com.breno.backend.service;

import com.breno.backend.domain.Usuario;
import com.breno.backend.presentation.request.UsuarioRequest;
import com.breno.backend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CadastroService {

    private final UsuarioRepository usuarioRepository;
    private final CorService corService;

    public Usuario save(UsuarioRequest usuarioRequest) {

        Usuario usuario = requestToEntity(usuarioRequest);
        usuario.setCor(corService.findById(usuarioRequest.getCorId()));

        return usuarioRepository.save(usuario);
    }

    private static Usuario requestToEntity(UsuarioRequest usuarioRequest) {
        return Usuario
                .builder()
                .cpf(usuarioRequest.getCpf())
                .email(usuarioRequest.getEmail())
                .observacoes(usuarioRequest.getObservacoes())
                .nome(usuarioRequest.getNome())
                .build();
    }

}
