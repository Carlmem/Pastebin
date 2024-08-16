package com.carlmem.pastebin.scheduler;

import com.carlmem.pastebin.service.HashService;
import com.carlmem.pastebin.service.HashServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@EnableAsync
@Component
@RequiredArgsConstructor
public class HashFillScheduler {

    private final static long LOAD_AMOUNT = 1_00_000;

    private final HashService hashService;

    private final RedisTemplate<String, String> template;

    @Async
    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void fill() {
        this.hashService.load(
                LOAD_AMOUNT - Optional.ofNullable(this.template.opsForSet().size(HashServiceImpl.HASH_KEY))
                        .orElse(0L)
        );
    }
}
