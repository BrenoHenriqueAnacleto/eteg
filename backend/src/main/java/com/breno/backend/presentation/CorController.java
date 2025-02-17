package com.breno.backend.presentation;

import com.breno.backend.domain.Cor;
import com.breno.backend.service.CorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cores")
@RequiredArgsConstructor
@Slf4j
public class CorController {
    private final CorService corService;

    @GetMapping
    public List<Cor> list() {
        return corService.list();
    }
}
