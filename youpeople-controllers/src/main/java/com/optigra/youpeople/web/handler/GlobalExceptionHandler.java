package com.optigra.youpeople.web.handler;

import com.optigra.youpeople.dto.ExceptionDTO;
import com.optigra.youpeople.exception.ContentNotFoundException;
import com.optigra.youpeople.exception.PersonUploadException;
import com.optigra.youpeople.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by romanmudryi on 04.08.15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<ExceptionDTO> contentNotFound(ContentNotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getValue(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonUploadException.class)
    public ResponseEntity<ExceptionDTO> personUpload(PersonUploadException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getValue(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> userAlreadyExists(UserAlreadyExistsException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getValue(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> handleException(TransactionSystemException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDTO> handleException(MethodArgumentNotValidException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getBindingResult().toString(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
