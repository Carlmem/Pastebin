package com.carlmem.pastebin.communication.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AmazonS3Service {

    String upload(MultipartFile contentFile);

    void deleteAll(List<String> fileNames);
}
