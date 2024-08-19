package com.carlmem.pastebin.communication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class CommunicationController {

    @PostMapping("/create")
    public String create(
            @RequestParam("contentFile") MultipartFile contentFile,
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date expiredTime
    ) {
        return "";
    }
}
