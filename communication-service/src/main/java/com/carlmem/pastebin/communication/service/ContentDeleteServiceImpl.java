package com.carlmem.pastebin.communication.service;

import com.carlmem.pastebin.communication.model.ContentEntity;
import com.carlmem.pastebin.communication.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentDeleteServiceImpl implements ContentDeleteService {

    private final ContentRepository contentRepository;

    private final AmazonS3Service amazonS3Service;

    @Override
    @Transactional
    public void deleteExpired() {
        final var expiredContent = this.contentRepository.findExpiredContents();
        this.amazonS3Service.deleteAll(
                expiredContent.stream()
                        .map(ContentEntity::getHash)
                        .toList()
        );
        this.contentRepository.deleteAll(expiredContent);
    }
}
