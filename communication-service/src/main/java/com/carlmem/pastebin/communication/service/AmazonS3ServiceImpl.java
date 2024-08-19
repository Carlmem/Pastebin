package com.carlmem.pastebin.communication.service;

import com.amazonaws.services.s3.AmazonS3;
import com.carlmem.pastebin.communication.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private final AmazonS3 amazonS3;

    private final String bucketName;

    public AmazonS3ServiceImpl(AmazonS3 amazonS3, @Value("${aws.s3.bucket}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    @Override
    public void upload(String hash, MultipartFile contentFile, Date date) {
        try {
            this.amazonS3.putObject(this.bucketName, hash, contentFile.getInputStream(), null);
        } catch (IOException e) {
            throw new FileUploadException("cannot upload file.", e);
        }
    }
}
