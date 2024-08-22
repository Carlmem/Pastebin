package com.carlmem.pastebin.communication.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.carlmem.pastebin.communication.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private final AmazonS3 amazonS3;

    private final String bucketName;

    public AmazonS3ServiceImpl(AmazonS3 amazonS3, @Value("${aws.s3.bucket}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    @Override
    public String upload(String hash, MultipartFile contentFile) {
        try {
            this.amazonS3.putObject(this.bucketName, hash, contentFile.getInputStream(), null);
            return this.amazonS3.getUrl(this.bucketName, hash).toString();
        } catch (IOException e) {
            throw new FileUploadException("cannot upload file.", e);
        }
    }

    @Override
    public void deleteAll(List<String> fileNames) {
        final var deleteRequest = new DeleteObjectsRequest(this.bucketName);
        deleteRequest.setKeys(
                fileNames.stream()
                        .map(DeleteObjectsRequest.KeyVersion::new)
                        .toList()
        );

        this.amazonS3.deleteObjects(deleteRequest);
    }
}
