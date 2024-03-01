package com.example.labjee.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException() {
        return "notFound";
    }
}
