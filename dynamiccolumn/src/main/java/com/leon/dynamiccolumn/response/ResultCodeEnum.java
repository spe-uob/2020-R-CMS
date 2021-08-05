package com.leon.dynamiccolumn.response;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(true, 20000, "success"),
    UNKNOWN_ERROR(false, 20001, "unknown error"),
    PARAM_ERROR(false, 20002, "Parameter error"),
    HTTP_CLIENT_ERROR(false, 20003, "http Client error"),
    NULL_POINT(false, 20004, "Null pointer exception"),
    UNEXIST_COLUMN(false, 20005, "Column does not exist"),
    UNEXIST_TABLE(false, 20006, "Table does not exist"),
    CONFLICT_TITLE(false, 20007, "The title to repeat"),
    ;

    // Response successful
    private Boolean success;
    // Response status code
    private Integer code;
    // Response information
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
