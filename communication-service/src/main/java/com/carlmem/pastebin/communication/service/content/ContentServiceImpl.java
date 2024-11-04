package com.carlmem.pastebin.communication.service.content;

import com.carlmem.pastebin.communication.dto.ContentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentEntityService contentEntityService;

    @Override
    @Transactional
    public ContentDto get(String hash) {
        final var contentEntity = this.contentEntityService.getByHash(hash);
        contentEntity.setViews(contentEntity.getViews() + 1);
        return ContentDto.builder()
                .fileUrl(contentEntity.getFileUrl())
                .views(contentEntity.getViews())
                .build();
    }
}
