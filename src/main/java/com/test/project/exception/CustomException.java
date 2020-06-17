package com.test.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class CustomException extends RuntimeException {

    private CustomException(String message) {
        super(message);
    }

    private CustomException(Throwable cause) {
        super(cause);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class InternalServerErrorException extends CustomException {
        public InternalServerErrorException(Throwable cause) {
            super(cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends CustomException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
