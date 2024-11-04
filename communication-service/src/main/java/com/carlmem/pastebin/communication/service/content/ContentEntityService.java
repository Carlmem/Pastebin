package com.carlmem.pastebin.communication.service.content;

import com.carlmem.pastebin.communication.domain.ContentEntity;

import java.util.Date;

public interface ContentEntityService {

    ContentEntity getByHash(String hash);

    ContentEntity create(String hash, String fileUrl, Date expiredDate);
}
