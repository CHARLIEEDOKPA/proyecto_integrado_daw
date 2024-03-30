package org.iesbelen.veterinario.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.iesbelen.veterinario.model.UserPassword;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController



public class LoginController {
    
    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserPassword userPassword) {

    }
    

}
