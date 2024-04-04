package org.iesbelen.veterinario.controllers;

import java.util.Optional;

import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.model.Doctor;
import org.iesbelen.veterinario.model.Duenyo;
import org.iesbelen.veterinario.model.UserPassword;
import org.iesbelen.veterinario.services.CredencialesService;
import org.iesbelen.veterinario.services.DoctorService;
import org.iesbelen.veterinario.services.DuenyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;




@RestController



public class LoginController {

    @Autowired
    private CredencialesService credencialesService;

    @Autowired
    private DuenyoService duenyoService;

    @Autowired
    private DoctorService doctorService;
    
    @PostMapping("login")
    public ResponseEntity<Credenciales> login(@RequestBody UserPassword userPassword, HttpSession httpSession) {
        Optional<Credenciales> opt = credencialesService.findCredencialByEmail(userPassword);
        if (opt.isPresent()) {
            Credenciales credenciales = opt.get();
            boolean loggedCorrectly = credenciales.getContrasenya().equals(userPassword.getContrasenya());
            if (loggedCorrectly) {
                httpSession.setAttribute("usuario", credenciales);        
                return new ResponseEntity<>(credenciales,HttpStatus.OK);
            }
        } 
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("prueba")
    public ResponseEntity<Credenciales> prueba(HttpSession httpSession) {
        return new ResponseEntity<Credenciales>(((Credenciales) httpSession.getAttribute("usuario")), HttpStatus.OK);
    }
    
    

}
