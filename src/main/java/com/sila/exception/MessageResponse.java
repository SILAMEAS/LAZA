package com.sila.exception;

import lombok.Data;

@Data
public class MessageResponse {
    private int status;
    private String message;
}
