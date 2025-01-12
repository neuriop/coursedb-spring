package com.neuro.coursedb.errors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private ApiError(){
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status){
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable e){
        this();
        this.status = status;
        debugMessage = e.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable e){
        this();
        this.status = status;
        this.message = message;
        debugMessage = e.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}
