package com.projeto.imoveis.exception.exceptionhandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto handler(MethodArgumentNotValidException ex){

        ExceptionDto exceptionDto = new ExceptionDto("Um ou mais campos estão invalidos!");

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for(FieldError fieldError : fieldErrors){
            String nome = fieldError.getField();
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            exceptionDto.adicionaCampoComErro(nome, mensagem);
        }

        return exceptionDto;
    }
}
