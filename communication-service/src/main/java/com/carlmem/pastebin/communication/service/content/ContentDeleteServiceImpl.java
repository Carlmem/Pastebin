package com.carlmem.pastebin.communication.service.content;

import com.carlmem.pastebin.communication.domain.ContentEntity;
import com.carlmem.pastebin.communication.repository.ContentRepository;
import com.carlmem.pastebin.communication.service.s3.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentDeleteServiceImpl implements ContentDeleteService {

    private final AmazonS3Service amazonS3Service;

    private final ContentRepository contentRepository;

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
