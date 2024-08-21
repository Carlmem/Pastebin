package com.carlmem.pastebin.communication.service;

import com.carlmem.pastebin.communication.model.ContentEntity;

import java.util.Date;

public interface ContentEntityService {

    ContentEntity getByHash(String hash);

    ContentEntity create(String hash, String fileUrl, String fileName, Date expiredDate);
}
