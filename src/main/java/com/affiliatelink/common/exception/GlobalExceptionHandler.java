
package com.affiliatelink.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProfileNotFound(
            ProfileNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(StatusInvalidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidStatusTransition(
            StatusInvalidException exception) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler (EmailExistException.class)
    public ResponseEntity<Map<String,String>> handleEmailExistException(
            EmailExistException exception
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error","email allready exists!"));
    }




        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> handleValidation(
                MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Validation failed");
            response.put("errors", errors);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }


}
