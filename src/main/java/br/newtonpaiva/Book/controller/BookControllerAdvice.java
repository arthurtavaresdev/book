package br.newtonpaiva.Book.controller;

import br.newtonpaiva.Book.exception.BadRequestException;
import br.newtonpaiva.Book.exception.NotFoundException;
import br.newtonpaiva.Book.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ex.printStackTrace();

        var response = new ErrorResponse()
                .withError(ex.getMessage())
                .withMessage(ex.getMessage())
                .withStatus(404)
                .withTimestamp(ZonedDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ex.printStackTrace();

        var response = new ErrorResponse()
                .withError(ex.getMessage())
                .withMessage(ex.getMessage())
                .withStatus(400)
                .withTimestamp(ZonedDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
