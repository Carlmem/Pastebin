package com.carlmem.pastebin.communication.repository;

import com.carlmem.pastebin.communication.domain.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, String> {

    @Query("SELECT c FROM ContentEntity c WHERE c.expiredDate < CURRENT_DATE")
    List<ContentEntity> findExpiredContents();
}
