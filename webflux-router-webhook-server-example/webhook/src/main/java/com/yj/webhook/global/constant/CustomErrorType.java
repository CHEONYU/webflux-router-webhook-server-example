package com.yj.webhook.global.constant;

public enum CustomErrorType {

    NO_X_TRACE_ID_ERROR(460, "There is no X-Trace-ID in Cafe24 request header");

    private final int status;
    private final String message;

    CustomErrorType(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
