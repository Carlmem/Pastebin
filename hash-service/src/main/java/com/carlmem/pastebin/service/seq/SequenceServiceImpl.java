package com.carlmem.pastebin.service.seq;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SequenceServiceImpl implements SequenceService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Long> getByAmount(long amount) {
        String req = "SELECT nextval('hash_sequence') FROM generate_series(1, ?)";
        return this.jdbcTemplate.queryForList(req, Long.class, amount);
    }
}
