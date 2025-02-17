package com.breno.backend.service;

import com.breno.backend.domain.Cor;
import com.breno.backend.exceptions.NotFoundException;
import com.breno.backend.repositories.CorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CorService {

    private final CorRepository corRepository;

    public List<Cor> list() {
        return corRepository.findAll();
    }

    public Cor findById(Long id) {
        Optional<Cor> optionalCor = corRepository.findById(id);

        if (optionalCor.isEmpty()) {
            throw new NotFoundException("Cor nao encontrada");
        }

        return optionalCor.get();
    }
}
