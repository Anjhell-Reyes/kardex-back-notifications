package com.kardex.infrastructure.exceptionHandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {
    NOTIFICATION_NOT_FOUND("Notification not found"),
    NOT_DATA_FOUND("No data found"),
    STATUS_NOT_FOUND("Status not found"),
    TYPE_NOT_FOUND("Type not found"),
    MESSAGE_NULL("Message must not be null"),
    PRODUCT_NAME_NULL("Product name must not be null"),
    PAGE_INVALID("Page index must not be less than zero"),
    MISSING_PARAMETER("The required parameter '%s' is missing."),
    EXPIRED_TIME("Your session has expired. Please log in again."),
    USER_ID_NULL("User id must not be null"),
    USER_FORBIDDEN("The user does not have permission to perform this action");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

}
