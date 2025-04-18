package com.kardex.infrastructure.exceptionHandler;

import com.kardex.domain.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(NotDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotificationNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOTIFICATION_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(TypeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTypeNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.TYPE_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleStatusNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.STATUS_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Map<String, String>> handleMissingServletRequestPartException(
            MissingServletRequestPartException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE,
                        String.format(ExceptionResponse.MISSING_PARAMETER.getMessage(), exception.getRequestPartName())));
    }

    @ExceptionHandler(MessageNotNullException.class)
    public ResponseEntity<Map<String, String>> handleDescriptionNotNullException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.MESSAGE_NULL.getMessage()));
    }

    @ExceptionHandler(UserIdNotNullException.class)
    public ResponseEntity<Map<String, String>> handleUserIdNotNullException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_ID_NULL.getMessage()));
    }

    @ExceptionHandler(ProductNameNotNullException.class)
    public ResponseEntity<Map<String, String>> handleCompanyNotNullException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_NAME_NULL.getMessage()));
    }

    @ExceptionHandler(InvalidPageIndexException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageIndexException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PAGE_INVALID.getMessage()));
    }
    
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, String>> handleExpiredJwtException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EXPIRED_TIME.getMessage()));
    }

    @ExceptionHandler(UserForbiddenException.class)
    public ResponseEntity<Map<String, String>> handleUserForbiddenException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_FORBIDDEN.getMessage()));
    }
}
