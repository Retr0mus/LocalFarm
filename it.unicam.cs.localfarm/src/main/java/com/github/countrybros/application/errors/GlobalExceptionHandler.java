package com.github.countrybros.application.errors;

import com.github.countrybros.model.user.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;

import java.util.List;

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

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<String> handleCustomException(InvalidRoleException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleAlreadyAssignedException.class)
    public ResponseEntity<String> handleCustomException(RoleAlreadyAssignedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);

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

    @ExceptionHandler(EventsNotFoundException.class)
    public ResponseEntity<String> handleCustomException(EventsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleCustomException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleCustomExtception(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == UserRole.class) {
            return ResponseEntity.badRequest().body(
                    "Invalid value '" + ex.getValue() + "' for '" + ex.getName() + "'. Valid roles: " + Arrays.toString(UserRole.values())
            );
        }
        return ResponseEntity.badRequest().body(
                "Invalid value '" + ex.getValue() + "' for '" + ex.getName() + "'."
        );
    }
}