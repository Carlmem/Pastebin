package com.carlmem.pastebin.communication.service;

import com.carlmem.pastebin.communication.client.HashServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BlockCreateServiceImpl implements BlockCreateService {

    private final HashServiceClient hashServiceClient;

    @Override
    public String create(MultipartFile multipartFile, Date expiredDate) {
        return null;
    }
}
