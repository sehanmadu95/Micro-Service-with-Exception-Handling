package com.example.Day5_Singer_MS.exception;

import com.example.Day5_Singer_MS.dto.CommonResponse;
import org.springframework.http.HttpStatus;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerExceptionHandler.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerExceptionHandler(InternalServerExceptionHandler e){
        return new ResponseEntity<>("Internal server exception : "+e.getMessage(), HttpStatus.FOUND);
    }


    @ExceptionHandler(RecordExistingException.class)
    public ProblemDetail handleRecordExistingHandler(RecordExistingException e){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.OK,e.getMessage());
        problemDetail.setStatus(HttpStatus.FOUND.value());
        problemDetail.setTitle("Record already exists");
        problemDetail.setType(URI.create("https://example.com/something-not-found"));
        problemDetail.setProperty("custom_property", "New Message");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<CommonResponse> handleItemNotFoundException(ItemNotFoundException e){
        return new ResponseEntity<>(CommonResponse.builder()
                .data(null)
                .message(e.getMessage())
                .responseCode(HttpStatus.NOT_FOUND)
                .build(),HttpStatus.NOT_FOUND);
    }

}
