package com.carlmem.pastebin.communication.service.content;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface ContentCreateService {

    String create(MultipartFile multipartFile, Date expiredDate);

}
