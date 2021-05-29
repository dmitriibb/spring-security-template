package com.dmbb.securitytemplate.controller;

import com.dmbb.securitytemplate.exception.TmpException;
import com.dmbb.securitytemplate.model.dto.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(TmpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO baseHandler(TmpException exception) {
        log.error(exception.getMessage());
        return new ExceptionDTO(exception.getMessage());
    }

}
