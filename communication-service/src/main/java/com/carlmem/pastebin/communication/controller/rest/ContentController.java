package com.carlmem.pastebin.communication.controller.rest;

import com.carlmem.pastebin.communication.dto.ContentDto;
import com.carlmem.pastebin.communication.dto.ErrorDto;
import com.carlmem.pastebin.communication.service.content.ContentCreateService;
import com.carlmem.pastebin.communication.service.content.ContentService;
import com.carlmem.pastebin.communication.util.WebConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping(WebConstants.FULL_WEB)
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    private final ContentCreateService contentCreateService;


    @Operation(
            summary = "create text block.",
            description = "creates text block and returns the hash to access the data.",
            parameters = {
                    @Parameter(name = "contentFile", description = "content text file"),
                    @Parameter(name = "date", description = "expiration date"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content= @Content(schema = @Schema(name = "Hash", description = "returns the hash to access the data"))
                    ),
                    @ApiResponse(
                            description = "Couldn't upload file.",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ErrorDto.class))
                    )
            }
    )
    @PostMapping("/contents")
    public String create(
            @RequestParam("contentFile") MultipartFile contentFile,
            @RequestParam("expiredDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date
    ) {
        return this.contentCreateService.create(contentFile, date);
    }

    @Operation(
            summary = "retrieve content.",
            description = "retrieve content by hash.",
            parameters = {
                    @Parameter(name = "hash", description = "hash to access data"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content= @Content(schema = @Schema(implementation = ContentDto.class))
                    ),
                    @ApiResponse(
                            description = "Couldn't find file.",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ErrorDto.class))
                    )
            }
    )
    @GetMapping("/contents/{hash}")
    public ContentDto getContent(@PathVariable("hash") String hash) {
        return this.contentService.get(hash);
    }

}
