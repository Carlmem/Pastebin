package com.carlmem.pastebin.communication.service.content;

import com.carlmem.pastebin.communication.domain.ContentEntity;
import com.carlmem.pastebin.communication.exception.EntityNotFoundException;
import com.carlmem.pastebin.communication.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class ContentEntityServiceImpl implements ContentEntityService {

    private final ContentRepository contentRepository;

    @Override
    @Transactional(readOnly = true)
    public ContentEntity getByHash(String hash) {
        return this.contentRepository.findById(hash)
                .orElseThrow(() -> new EntityNotFoundException("cannot find content with hash: " + hash));
    }

    @Override
    @Transactional
    public ContentEntity create(String hash, String url, Date expiredDate) {
        return this.contentRepository.save(
                new ContentEntity()
                        .setHash(hash)
                        .setFileUrl(url)
                        .setExpiredDate(expiredDate)
                        .setViews(0L)
        );
    }
}
