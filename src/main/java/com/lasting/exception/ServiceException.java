package com.lasting.exception;

public final class ServiceException extends RuntimeException{
    private static final long serialVersionUID=1L;
    private Integer code;
    private String message;
    private String detailsMessage;

    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message,Integer code) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetailsMessage() {
        return detailsMessage;
    }

    public ServiceException setDetailsMessage(String detailsMessage) {
        this.detailsMessage = detailsMessage;
        return this;
    }
}
