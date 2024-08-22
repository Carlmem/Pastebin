package com.carlmem.pastebin.communication.service;

import com.carlmem.pastebin.communication.client.HashServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ContentCreateServiceImpl implements ContentCreateService {

    private final AmazonS3Service amazonS3Service;

    private final HashServiceClient hashServiceClient;

    private final ContentEntityService contentEntityService;

    @Override
    public String create(MultipartFile multipartFile, Date expiredDate) {
        final var hash = this.hashServiceClient.generate();
        final var fileUrl = this.amazonS3Service.upload(hash, multipartFile);
        this.contentEntityService.create(hash, fileUrl, expiredDate);
        return hash;
    }
}
