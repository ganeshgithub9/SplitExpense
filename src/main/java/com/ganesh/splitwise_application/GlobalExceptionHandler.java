package com.ganesh.splitwise_application;

import com.ganesh.splitwise_application.exceptions.AmountExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
ResponseEntity<String> handleSQLException(SQLException e){
        String s=e.getMessage();
        if(s.contains("mail"))
            return new ResponseEntity<>("Mail already exists",HttpStatus.OK);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<String> handleSQLException(NullPointerException e){
        return new ResponseEntity<>("ID Not Found",HttpStatus.OK);
    }

    @ExceptionHandler(AmountExceededException.class)
    ResponseEntity<String> handleSQLException(AmountExceededException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
}
