package org.iesbelen.veterinario.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.model.Recomendacion;
import org.iesbelen.veterinario.model.RecomendacionRequest;
import org.iesbelen.veterinario.services.RecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("recomendacion")


public class RecomendacionController {

    @Autowired
    private RecomendacionService recomendacionService;

    @PostMapping("add")
    public ResponseEntity<Recomendacion> saveRecomendacion(@RequestBody @Valid RecomendacionRequest recomendacionRequest,HttpSession httpSession, BindingResult bindingResult) {
        
        
        
        Credenciales credenciales = (Credenciales) httpSession.getAttribute("usuario");
        if (credenciales != null && credenciales.getRol().equals(recomendacionRequest.getRol())) {
            boolean hasError = bindingResult.hasFieldErrors();

            if (hasError) {
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }

            Recomendacion recomendacion = recomendacionService.saveRecomendación(recomendacionRequest, credenciales);
            return recomendacion != null ? new ResponseEntity<>(recomendacion,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> invalidFormat() {
        return new ResponseEntity<>("Invalid Format",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> invalidJSONFormatException() {
        return new ResponseEntity<>("Invalid JSON Format", HttpStatus.BAD_REQUEST);
    } 
     
    @ExceptionHandler(DataIntegrityViolationException.class) 
    public ResponseEntity<String> DataIntegrityViolationException() {
        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    } 
}
