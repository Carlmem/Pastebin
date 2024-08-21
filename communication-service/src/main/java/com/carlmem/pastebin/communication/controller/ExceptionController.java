package com.carlmem.pastebin.communication.controller;

import com.carlmem.pastebin.communication.dto.ErrorDto;
import com.carlmem.pastebin.communication.exception.EntityNotFoundException;
import com.carlmem.pastebin.communication.exception.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({FileUploadException.class})
    public ErrorDto internal(Exception e) {
        return new ErrorDto(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorDto notFound(Exception e) {
        return new ErrorDto(e.getMessage());
    }
}
