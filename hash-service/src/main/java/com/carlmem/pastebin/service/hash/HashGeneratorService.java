package com.carlmem.pastebin.service.hash;

import java.util.List;

public interface HashGeneratorService {

    List<String> generate(long amount);

}