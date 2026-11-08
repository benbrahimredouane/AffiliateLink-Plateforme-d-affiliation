
package com.affiliatelink.common.exception;

import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCampaignNotFound(CampaignNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Map<String, String>> handleInvalidSort(PropertyReferenceException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Invalid sort property: " + exception.getPropertyName()));
    }

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleOffernoufound(
            OfferNotFoundException exception
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","that offer not found"));
    }
    @ExceptionHandler(AdvertiserNotActiveException.class)
    public ResponseEntity<Map<String,String>> handleAdvertisernotActive(
            AdvertiserNotActiveException exception
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","that  advertiser not active"));
    }

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
