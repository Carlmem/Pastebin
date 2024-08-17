package com.carlmem.pastebin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.zip.CRC32;

@Service
@RequiredArgsConstructor
public class HashGeneratorServiceImpl implements HashGeneratorService {

    private final SequenceService sequenceService;

    @Override
    public List<String> generate(long amount) {
        return this.sequenceService.getByAmount(amount).stream()
                .map(num -> ByteBuffer.allocate(Long.BYTES).putLong(num).array())
                .map(arr -> {
                    var hash = new CRC32();
                    hash.update(arr);
                    return hash.getValue();
                })
                .map(num -> String.format("%08X", num))
                .toList();
    }
}
