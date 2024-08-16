package com.carlmem.pastebin.service;

public interface HashService {

    String getOrLoad();

    void load(long amount);
}
