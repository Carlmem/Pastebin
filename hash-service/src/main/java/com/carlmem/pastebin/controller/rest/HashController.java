package com.carlmem.pastebin.controller.rest;

import com.carlmem.pastebin.dto.ErrorDto;
import com.carlmem.pastebin.service.HashService;
import com.carlmem.pastebin.util.WebConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WebConstants.API)
@RequiredArgsConstructor
public class HashController {

    private final HashService hashService;

    @Operation(
            summary = "generate hash.",
            description = "generates hash for data.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content= @Content(schema = @Schema(name = "Hash", description = "returns the hash to access the data"))
                    ),
                    @ApiResponse(
                            description = "Couldn't generate the hash.",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ErrorDto.class))
                    )
            }
    )
    @GetMapping("/hashes")
    public String generate() {
        return this.hashService.getOrLoad();
    }
}
