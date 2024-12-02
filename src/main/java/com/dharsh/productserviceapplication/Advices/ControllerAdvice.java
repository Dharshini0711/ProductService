package com.dharsh.productserviceapplication.Advices;

import com.dharsh.productserviceapplication.Dtos.ErrorDto;
import com.dharsh.productserviceapplication.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(productNotFoundException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
