package com.carlmem.pastebin.communication.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface AmazonS3Service {

    void upload(String hash, MultipartFile contentFile, Date expiredDate);
}
