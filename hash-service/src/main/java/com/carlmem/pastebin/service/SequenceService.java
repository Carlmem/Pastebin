package com.carlmem.pastebin.service;

import java.util.List;

public interface SequenceService {

    List<Long> getByAmount(long amount);
}
