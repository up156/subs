package com.subs.demo.ex.handler;

import com.subs.demo.dto.ErrorDto;
import com.subs.demo.ex.SubNotFoundException;
import com.subs.demo.ex.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class SubExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class, SubNotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFoundException(RuntimeException ex) {
        log.error("not found ex: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorDto.builder()
                .message("not found")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleOtherException(Exception ex, WebRequest request) {
        log.error("some problem: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorDto.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}