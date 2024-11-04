package com.carlmem.pastebin.communication.controller;

import com.carlmem.pastebin.communication.controller.rest.ContentController;
import com.carlmem.pastebin.communication.dto.ContentDto;
import com.carlmem.pastebin.communication.service.content.ContentCreateService;
import com.carlmem.pastebin.communication.service.content.ContentService;
import com.carlmem.pastebin.communication.util.WebConstants;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class)
public class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @MockBean
    private ContentCreateService contentCreateService;

    @Test
    public void testCreate() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "contentFile", "file.txt", "text/plain", "content".getBytes()
        );
        Mockito.when(this.contentCreateService.create(any(), any())).thenReturn("HASH");

        this.mockMvc.perform(multipart(WebConstants.FULL_WEB + "/contents")
                        .file(file)
                        .param("expiredDate", "2024-12-31"))
                .andExpect(status().isOk())
                .andExpect(content().string("HASH"));
    }

    @Test
    public void testGetContent() throws Exception {
        final var contentDto = new ContentDto(100, "fileUrl");
        final var path = WebConstants.FULL_WEB + "/contents/hash";

        Mockito.when(this.contentService.get(anyString())).thenReturn(contentDto);
        this.mockMvc.perform(get(path)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"views\":100,\"fileUrl\":\"fileUrl\"}"));
    }
}