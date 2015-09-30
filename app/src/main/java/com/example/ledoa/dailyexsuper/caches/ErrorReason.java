package com.example.ledoa.dailyexsuper.caches;

public class ErrorReason {

    public enum ErrorType {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    private ErrorType type;

    private Throwable cause;

    public ErrorReason() {
    }


    public ErrorReason(ErrorType type, Throwable cause) {
        this.type = type;
        this.cause = cause;
    }

    public ErrorType getType() {
        return type;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
