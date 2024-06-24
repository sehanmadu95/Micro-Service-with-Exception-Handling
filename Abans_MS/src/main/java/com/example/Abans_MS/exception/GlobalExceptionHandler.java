package com.example.Abans_MS.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ProblemDetail handleItemNotFoundExeption(ItemNotFoundException msg){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,msg.getMessage());
        problemDetail.setTitle("No Records found");
        problemDetail.setType(URI.create("https://example.com/something-not-found"));
        //problemDetail.setProperty("custom_property", "New Message");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;


    }

    @ExceptionHandler(InternalServerExceptionHandler.class)
    public ProblemDetail handleInternalServerExceptionHandler(InternalServerExceptionHandler msg){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,msg.getMessage());
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(URI.create("https://example.com/something-not-found"));
        //problemDetail.setProperty("custom_property", "New Message");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;


    }

    @ExceptionHandler(NullObjectFound.class)
    public ProblemDetail handleNullObjectExceptionHandler(NullObjectFound e){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setType(URI.create("https://example.com/something-wrong"));
        problemDetail.setProperty("timestamp",Instant.now());
        return  problemDetail;
    }

    @ExceptionHandler(RecordExistingException.class)
    public ProblemDetail handleRecordExistingHandler(RecordExistingException e){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.OK,e.getMessage());
        problemDetail.setStatus(HttpStatus.FOUND.value());
        problemDetail.setTitle("Record already exists");
        problemDetail.setType(URI.create("https://example.com/something-not-found"));
        //problemDetail.setProperty("custom_property", "New Message");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
