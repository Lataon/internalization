package com.gmail.elbaglikov.exceptions;

public class InitDataException extends Exception {
    public InitDataException() {
    }

    public InitDataException(String message) {
        super(message);
    }

    public InitDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitDataException(Throwable cause) {
        super(cause);
    }

    public InitDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
