package com.carlmem.pastebin.communication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Objects;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "content")
public class ContentEntity {

    @Id
    @Column(name = "hash", length = 10)
    private String hash;

    @Column(name = "views")
    private Long views;

    @Column(name = "file_url")
    private String fileUrl;

    @Temporal(TemporalType.DATE)
    @Column(name = "expired_date")
    private Date expiredDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentEntity that = (ContentEntity) o;
        return Objects.equals(hash, that.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}
