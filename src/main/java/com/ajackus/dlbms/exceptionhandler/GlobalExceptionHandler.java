package com.ajackus.dlbms.exceptionhandler;

import com.ajackus.dlbms.excpetion.BookAlreadyExistException;
import com.ajackus.dlbms.excpetion.BookNotFoundException;
import com.ajackus.dlbms.excpetion.FieldValidationException;
import com.ajackus.dlbms.excpetion.errormessage.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String errorMessage = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        apiErrorResponse.setMessage(errorMessage);
        apiErrorResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(bookNotFoundException.getMessage());
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleBookNotFoundException(BookAlreadyExistException bookAlreadyExistException) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(bookAlreadyExistException.getMessage());
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    @ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleBookNotFoundException(FieldValidationException fieldValidationException) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(fieldValidationException.getMessage());
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

}
