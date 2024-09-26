package com.onlinedemoshop.demoshop.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerDoesNotExistException extends RuntimeException{
    public CustomerDoesNotExistException(String message) {
        super(message);
    }
}
