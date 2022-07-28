package com.example.natube.exception;


public class CustomException extends RuntimeException{ // Business로직 상으로 Error를 일으키는 경우들에는 RuntimeException을 상속한 CustomException을 만들어서 처리

    private ErrorCode errorCode;

    public CustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

}
