package com.carlmem.pastebin.communication.service;

import com.carlmem.pastebin.communication.dto.ContentDto;

public interface ContentService {

    ContentDto get(String hash);
}
