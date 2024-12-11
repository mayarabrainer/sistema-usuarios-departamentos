package br.com.projeto.userdept.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;
    private final long timestamp;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @JsonProperty("error")
    public String getError() {
        return error;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("timestamp")
    public long getTimestamp() {
        return timestamp;
    }
}
