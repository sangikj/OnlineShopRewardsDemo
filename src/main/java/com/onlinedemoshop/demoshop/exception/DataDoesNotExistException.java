package com.onlinedemoshop.demoshop.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DataDoesNotExistException extends RuntimeException{
    public DataDoesNotExistException(String message) {
        super(message);
    }
}
