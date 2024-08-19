package com.carlmem.pastebin.communication.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface BlockCreateService {

    String create(MultipartFile multipartFile, Date expiredDate);

}
