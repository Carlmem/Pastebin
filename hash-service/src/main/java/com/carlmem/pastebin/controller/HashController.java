package com.carlmem.pastebin.controller;

import com.carlmem.pastebin.service.HashService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HashController {

    private final HashService hashService;

    @GetMapping("/generate")
    public String generate() {
        return this.hashService.getOrLoad();
    }
}
