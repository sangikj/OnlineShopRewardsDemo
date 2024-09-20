package com.onlinedemoshop.demoshop.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InvalidMonthRangeException extends RuntimeException {
    public InvalidMonthRangeException(String message) {
        super(message);
    }

}
