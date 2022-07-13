package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor // This DTO is useful to manage all the API exceptions
public class APIErrorDTO {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
