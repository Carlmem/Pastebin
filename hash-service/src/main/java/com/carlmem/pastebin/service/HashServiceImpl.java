package com.carlmem.pastebin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashServiceImpl implements HashService {

    public static final String HASH_KEY = "hash_key";

    private final RedisTemplate<String, String> template;

    private final HashGeneratorService hashGeneratorService;

    @Override
    public String getOrLoad() {
        final var result = this.template.opsForSet().pop(HASH_KEY);
        return result == null ? this.hashGeneratorService.generate() : result;
    }
}
