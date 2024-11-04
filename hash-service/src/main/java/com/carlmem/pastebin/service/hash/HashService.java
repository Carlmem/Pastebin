package com.carlmem.pastebin.service.hash;

public interface HashService {

    String getOrLoad();

    void load(long amount);
}
