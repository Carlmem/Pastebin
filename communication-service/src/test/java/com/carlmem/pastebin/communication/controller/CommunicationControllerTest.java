package com.carlmem.pastebin.communication.controller;

import com.carlmem.pastebin.communication.dto.ContentDto;
import com.carlmem.pastebin.communication.service.ContentCreateService;
import com.carlmem.pastebin.communication.service.ContentService;
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

@WebMvcTest(CommunicationController.class)
public class CommunicationControllerTest {

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
        Mockito.when(contentCreateService.create(any(), any())).thenReturn("HASH");

        this.mockMvc.perform(multipart("/create")
                        .file(file)
                        .param("expiredDate", "2024-12-31"))
                .andExpect(status().isOk())
                .andExpect(content().string("HASH"));
    }

    @Test
    public void testGetPageContent() throws Exception {
        final var contentDto = new ContentDto(100, "fileUrl");
        final var path = "/content/hash";

        Mockito.when(contentService.get(anyString())).thenReturn(contentDto);
        this.mockMvc.perform(get(path)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"views\":100,\"fileUrl\":\"fileUrl\"}"));
    }
}