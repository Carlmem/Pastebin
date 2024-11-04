package com.carlmem.pastebin.service.hash;

import com.carlmem.pastebin.exception.GenerateException;
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
        if (result != null) {
            return result;
        }

        return this.hashGeneratorService.generate(1).stream().findFirst()
                .orElseThrow(() -> new GenerateException("Cannot generate hash"));
    }

    @Override
    public void load(long amount) {
        final var generateHashes = this.hashGeneratorService.generate(amount).toArray(String[]::new);
        if (generateHashes.length == 0) {
            return;
        }

        this.template.opsForSet().add(HASH_KEY, generateHashes);
    }
}
