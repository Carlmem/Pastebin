package com.carlmem.pastebin.service;

import io.netty.handler.codec.base64.Base64Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashGeneratorServiceImpl implements HashGeneratorService {

    private final SequenceService sequenceService;

    @Override
    public List<String> generate(long amount) {
        return this.sequenceService.getByAmount(amount).stream()
                .map(num -> ByteBuffer.allocate(Long.BYTES).putLong(num).array())
                .map(arr -> Base64.getEncoder().encodeToString(arr))
                .toList();
    }
}
