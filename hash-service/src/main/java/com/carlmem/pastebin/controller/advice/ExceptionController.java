package com.carlmem.pastebin.controller.advice;

import com.carlmem.pastebin.dto.ErrorDto;
import com.carlmem.pastebin.exception.GenerateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({GenerateException.class})
    public ErrorDto internal(Exception e) {
        return new ErrorDto(e.getMessage());
    }
}
