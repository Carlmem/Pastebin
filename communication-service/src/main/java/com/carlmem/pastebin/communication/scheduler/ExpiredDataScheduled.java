package com.carlmem.pastebin.communication.scheduler;

import com.carlmem.pastebin.communication.service.content.ContentDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@RequiredArgsConstructor
public class ExpiredDataScheduled {

    private final ContentDeleteService contentDeleteService;

    @Async
    @Scheduled(cron = "1 0 * * * *")
    public void expiredDataScheduler() {
        this.contentDeleteService.deleteExpired();
    }
}
