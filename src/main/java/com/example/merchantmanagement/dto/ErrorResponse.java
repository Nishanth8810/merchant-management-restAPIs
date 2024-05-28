package com.example.merchantmanagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class ErrorResponse {
    private LocalDate timeStamp; // The date when the error occurred
    private int status;          // The HTTP status code of the error
    private String error;        // A brief description of the error status
    private String message;      // A detailed error message
    private String path;

    public ErrorResponse(LocalDate now, int value, String reasonPhrase, String anInternalErrorOccurred, String description) {
    }
}
