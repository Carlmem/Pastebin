package com.carlmem.pastebin.loader;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SequenceLoader {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void load() {
        String req = "CREATE SEQUENCE IF NOT EXISTS hash_sequence START WITH 1 INCREMENT BY 1";
        this.jdbcTemplate.execute(req);
    }
}
