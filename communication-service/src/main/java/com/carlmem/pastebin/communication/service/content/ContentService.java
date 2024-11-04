package com.carlmem.pastebin.communication.service.content;

import com.carlmem.pastebin.communication.dto.ContentDto;

public interface ContentService {

    ContentDto get(String hash);
}
