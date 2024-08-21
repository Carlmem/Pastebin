package com.carlmem.pastebin.communication.dto;

import lombok.Builder;

@Builder
public record ContentDto(long views, String fileUrl) {
}
