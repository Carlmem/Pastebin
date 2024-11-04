package com.carlmem.pastebin.communication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ContentDto(

        @Schema(name = "views", description = "amount of views")
        long views,

        @Schema(name = "fileUrl", description = "url of the file to download from amazon s3")
        String fileUrl
) {
}
