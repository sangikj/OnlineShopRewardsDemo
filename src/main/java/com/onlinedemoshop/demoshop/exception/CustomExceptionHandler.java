package com.onlinedemoshop.demoshop.exception;

import com.onlinedemoshop.demoshop.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.onlinedemoshop.demoshop.constant.CommonConstants.*;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleInvalidDateFormatException(Exception ex) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code(INVALID_DATE_FORMAT_CODE)
                .message(INVALID_DATE_FORMAT_MESSAGE)
                .action(INVALID_DATE_FORMAT_ACTION)
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidMonthRangeException.class)
    public ResponseEntity<?> handleInvalidMonthRangeException(Exception ex) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code(INVALID_MONTH_RANGE_CODE)
                .message(ex.getMessage())
                .action(INVALID_MONTH_RANGE_ACTION)
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("Internal Server Error")
                .message(ex.getMessage())
                .action(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}


