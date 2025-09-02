package com.github.countrybros.application.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundInRepositoryException.class)
    public ResponseEntity<String> handleCustomException(NotFoundInRepositoryException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImpossibleRequestException.class)
    public ResponseEntity<String> handleCustomException(ImpossibleRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestAlreadySatisfiedException.class)
    public ResponseEntity<String> handleCustomException(RequestAlreadySatisfiedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(NotEnoughItemsException.class)
    public ResponseEntity<String> handleCustomException(NotEnoughItemsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SevereCodingErrorException.class)
    public ResponseEntity<String> handleCustomException(SevereCodingErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FunctionNotAvailableException.class)
    public ResponseEntity<String> handleCustomException(FunctionNotAvailableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleCustomException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleCustomException(ExternalError ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Log dell'errore
        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error");
    }
}