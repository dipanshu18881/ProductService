package com.enterpriseproject.productservice.ControllerAdvices;

import com.enterpriseproject.productservice.DTOs.ExceptionDto;
import com.enterpriseproject.productservice.Exceptions.ProductDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Void> handleRArithmeticException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductDoesNotExistException.class)
    public ResponseEntity<ExceptionDto> handleProductDoesNotExistException(ProductDoesNotExistException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Product Not Found Exception");
        exceptionDto.setDetail(e.getMessage());

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
