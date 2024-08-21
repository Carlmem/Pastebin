package com.carlmem.pastebin.communication.service;

import com.carlmem.pastebin.communication.exception.EntityNotFoundException;
import com.carlmem.pastebin.communication.model.ContentEntity;
import com.carlmem.pastebin.communication.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class ContentEntityServiceImpl implements ContentEntityService {

    private final ContentRepository contentRepository;

    @Override
    public ContentEntity getByHash(String hash) {
        return this.contentRepository.findById(hash)
                .orElseThrow(() -> new EntityNotFoundException("cannot find content with hash: " + hash));
    }

    @Override
    public ContentEntity create(String hash, String url, String fileName, Date expiredDate) {
        return this.contentRepository.save(
                new ContentEntity()
                        .setHash(hash)
                        .setFileName(fileName)
                        .setFileUrl(url)
                        .setExpiredDate(expiredDate)
                        .setViews(0L)
        );
    }
}
