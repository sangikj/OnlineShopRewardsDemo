package com.onlinedemoshop.demoshop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDetails {
    // Error message please add attribute here

    private String code;
    private String message;
    private String action;

}
