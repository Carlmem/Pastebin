package com.carlmem.pastebin.communication.controller;

import com.carlmem.pastebin.communication.dto.ContentDto;
import com.carlmem.pastebin.communication.service.ContentCreateService;
import com.carlmem.pastebin.communication.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class CommunicationController {

    private final ContentService contentService;

    private final ContentCreateService contentCreateService;

    @PostMapping("/create")
    public String create(
            @RequestParam("contentFile") MultipartFile contentFile,
            @DateTimeFormat(pattern="yyyy-MM-dd HH") Date expiredTime
    ) {
        return this.contentCreateService.create(contentFile, expiredTime);
    }

    @GetMapping("/content{hash}")
    public ResponseEntity<ContentDto> getPageContent(@PathVariable("hash") String hash) {
        return ResponseEntity.ok()
                .contentType(MediaType.ALL)
                .body(this.contentService.get(hash));
    }
}
