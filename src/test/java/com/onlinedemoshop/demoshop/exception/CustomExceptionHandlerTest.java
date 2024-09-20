package com.onlinedemoshop.demoshop.exception;


import com.onlinedemoshop.demoshop.model.ErrorDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.onlinedemoshop.demoshop.constant.CommonConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void testHandleInvalidDateFormatException() {

        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException("value", String.class, "name", null, null);


        ResponseEntity<?> responseEntity = customExceptionHandler.handleInvalidDateFormatException(ex);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        System.out.println(errorDetails.getCode() + " " + errorDetails.getMessage() + " " + errorDetails.getAction());
        assertEquals(INVALID_DATE_FORMAT_CODE, errorDetails.getCode());
        assertEquals(INVALID_DATE_FORMAT_MESSAGE, errorDetails.getMessage());
        assertEquals(INVALID_DATE_FORMAT_ACTION, errorDetails.getAction());
    }

    @Test
    public void testHandleInvalidMonthRangeException() {

        InvalidMonthRangeException ex = new InvalidMonthRangeException(INVALID_MONTH_RANGE_MESSAGE);


        ResponseEntity<?> responseEntity = customExceptionHandler.handleInvalidMonthRangeException(ex);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals(INVALID_MONTH_RANGE_CODE, errorDetails.getCode());
        assertEquals(INVALID_MONTH_RANGE_MESSAGE, errorDetails.getMessage());
        assertEquals(INVALID_MONTH_RANGE_ACTION, errorDetails.getAction());
    }

    @Test
    public void testHandleGlobalException() {

        Exception ex = new Exception("Global exception");
        when(webRequest.getDescription(false)).thenReturn("description");


        ResponseEntity<?> responseEntity = customExceptionHandler.handleGlobalException(ex, webRequest);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals("Internal Server Error", errorDetails.getCode());
        assertEquals("Global exception", errorDetails.getMessage());
        assertEquals("description", errorDetails.getAction());
    }
}