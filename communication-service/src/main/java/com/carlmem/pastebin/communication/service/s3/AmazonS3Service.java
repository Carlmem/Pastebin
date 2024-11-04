package com.carlmem.pastebin.communication.service.s3;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AmazonS3Service {

    String upload(String hash, MultipartFile contentFile);

    void deleteAll(List<String> fileNames);
}
