package com.carlmem.pastebin.service.seq;

import java.util.List;

public interface SequenceService {

    List<Long> getByAmount(long amount);
}
