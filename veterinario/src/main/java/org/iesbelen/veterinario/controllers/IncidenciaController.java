package org.iesbelen.veterinario.controllers;

import java.util.List;
import java.util.Optional;

import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.model.Incidencia;
import org.iesbelen.veterinario.model.IncidenciaRequest;
import org.iesbelen.veterinario.model.Mascota;
import org.iesbelen.veterinario.services.IncidenciaService;
import org.iesbelen.veterinario.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController

@RequestMapping("incidencia")

public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("list")
    public ResponseEntity<List<Incidencia>> getMethodName(HttpSession httpSession) {

        Credenciales credenciales = (Credenciales) httpSession.getAttribute("usuario");

        if (isNotNullAndADuenyo(credenciales)) {

            List<Incidencia> listIncidencias = incidenciaService
                    .getListIncidenciaByIdDuenyo(credenciales.getId_doctor_duenyo());
            return new ResponseEntity<>(listIncidencias, HttpStatus.OK);
        }

        return new ResponseEntity<List<Incidencia>>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("add")
    public ResponseEntity<Incidencia> postMethodName(@RequestBody @Valid IncidenciaRequest incidenciaRequest,
            BindingResult bindingResult, HttpSession httpSession) {
        Credenciales credenciales = (Credenciales) httpSession.getAttribute("usuario");

        if (credenciales != null) {
            boolean hasError = bindingResult.hasFieldErrors();

            if (hasError) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (incidenciaRequest != null && incidenciaRequest.getRol().equals("duenyo")) {

                if (ownersPet(incidenciaRequest, credenciales)) {
                    Incidencia newIncidencia = incidenciaService.saveIncidencia(incidenciaRequest);
                    return new ResponseEntity<>(newIncidencia, HttpStatus.CREATED);
                }

            }
        }

        return new ResponseEntity<Incidencia>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidFormatException.class)
    
    public ResponseEntity<String> invalidFormatException() {
        return new ResponseEntity<>("Invalid Format", HttpStatus.BAD_REQUEST);
    } 

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> invalidJSONFormatException() {
        return new ResponseEntity<>("Invalid JSON Format", HttpStatus.BAD_REQUEST);
    } 

    private boolean ownersPet(IncidenciaRequest incidenciaRequest, Credenciales credenciales) {
        long id = incidenciaRequest.getId_mascota();
        Optional<Mascota> opt = mascotaService.getMascotaById(id);
        return opt.isPresent() && opt.get().getId_duenyo() == credenciales.getId_doctor_duenyo();
    }

    private boolean isNotNullAndADuenyo(Credenciales credenciales) {
        return credenciales != null & credenciales.getRol().equals("duenyo");
    }

}