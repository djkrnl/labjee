package com.example.labjee.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

// Tydzień 7 - zasada pojedynczej odpowiedzialności - klasa zajmuje się obsługą wyjątków wykrytych w aplikacji poprzez przekierowywanie do odpowiednich widoków, co jest jej jedynym zadaniem
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException() {
        return "notFound";
    }
}
