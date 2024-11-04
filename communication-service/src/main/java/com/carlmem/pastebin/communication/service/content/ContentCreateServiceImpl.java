package com.carlmem.pastebin.communication.service.content;

import com.carlmem.pastebin.communication.client.HashClient;
import com.carlmem.pastebin.communication.service.s3.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ContentCreateServiceImpl implements ContentCreateService {

    private final HashClient hashClient;

    private final AmazonS3Service amazonS3Service;

    private final ContentEntityService contentEntityService;

    @Override
    public String create(MultipartFile multipartFile, Date expiredDate) {
        final var hash = this.hashClient.generate();
        final var fileUrl = this.amazonS3Service.upload(hash, multipartFile);
        this.contentEntityService.create(hash, fileUrl, expiredDate);
        return hash;
    }
}
