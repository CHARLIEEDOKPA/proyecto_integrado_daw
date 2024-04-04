package org.iesbelen.veterinario.controllers;

import java.util.List;

import org.iesbelen.veterinario.model.Cita;
import org.iesbelen.veterinario.model.CitaRequest;
import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@RestController

@RequestMapping("cita")

public class CitaController {
    

    @Autowired
    private CitaService citaService;

    @GetMapping("get/mascota")
    public ResponseEntity<List<Cita>> getCitasOfMascotas(@RequestBody @Valid CitaRequest citaRequest, HttpSession httpSession) {
        Credenciales credenciales = (Credenciales) httpSession.getAttribute("usuario");
        if (credenciales != null &&  credenciales.getRol().equals(citaRequest.getRol())) {
            List<Cita> citas = citaService.getCitasByIdMascota(credenciales.getId());
            return new ResponseEntity<>(citas,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

       @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> invalidJSONFormatException() {
        return new ResponseEntity<>("Invalid JSON Format or JSON missing", HttpStatus.BAD_REQUEST);
    } 

    @GetMapping("get/doctor")
    public ResponseEntity<List<Cita>> getCitasOfDoctors(@RequestBody CitaRequest citaRequest, HttpSession httpSession) {
        Credenciales credenciales = (Credenciales) httpSession.getAttribute("usuario");
        if (credenciales != null && 
        credenciales.getRol().equals(citaRequest.getRol()) && 
        credenciales.getRol().equals("doctor")) {
            List<Cita> citas = citaService.getCitasByIdDoctor(credenciales.getId());
            return new ResponseEntity<>(citas,HttpStatus.OK);  
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    

    

}
