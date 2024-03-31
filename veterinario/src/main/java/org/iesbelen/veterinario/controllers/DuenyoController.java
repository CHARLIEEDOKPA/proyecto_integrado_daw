package org.iesbelen.veterinario.controllers;

import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.model.Doctor;
import org.iesbelen.veterinario.model.Duenyo;
import org.iesbelen.veterinario.services.CredencialesService;
import org.iesbelen.veterinario.services.DuenyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

@RequestMapping("duenyo")

public class DuenyoController {

    @Autowired
    private DuenyoService duenyoService;

    @Autowired
    private CredencialesService credencialesService;

    @PostMapping("add")
    public ResponseEntity addDuenyo(@RequestBody Duenyo duenyo, HttpSession httpSession) {
        Credenciales credenciales = (Credenciales) httpSession.getAttribute("usuario");
        if (credenciales != null && credenciales.getRol().equals("administrador")) {
            Duenyo createdDuenyo = duenyoService.addDuenyo(duenyo);
            credencialesService.addDuenyoCredencial(createdDuenyo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
