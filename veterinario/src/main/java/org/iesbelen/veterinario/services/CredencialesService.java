package org.iesbelen.veterinario.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.iesbelen.veterinario.model.AuthResponse;
import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.model.Doctor;
import org.iesbelen.veterinario.model.Duenyo;
import org.iesbelen.veterinario.model.RegisterRequest;
import org.iesbelen.veterinario.model.UserPassword;
import org.iesbelen.veterinario.repo.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredencialesService {
    
    @Autowired
    private CredencialesRepository credencialesRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager auth;

	@Autowired
    PasswordEncoder passwordEncoder;


	public void addDoctorCredencial(Doctor doctor) {
		Credenciales credenciales =  new Credenciales();
		credenciales.setContrasenya("iesbelen");
		credenciales.setEmail(doctor.getEmail());
		credenciales.setId_doctor_duenyo(doctor.getId());
		credenciales.setRol("doctor");
		credencialesRepository.save(credenciales);
	}

	public void addDuenyoCredencial(Duenyo duenyo) {
		Credenciales credenciales =  new Credenciales();
		credenciales.setContrasenya("iesbelen");
		credenciales.setEmail(duenyo.getEmail());
		credenciales.setId_doctor_duenyo(duenyo.getId());
		credenciales.setRol("duenyo");
		credencialesRepository.save(credenciales);
	}

	public void addCredencial(Credenciales c) {
		credencialesRepository.save(c);
	}

	public Credenciales buildCredencial(RegisterRequest registerRequest, long id, String rol) {
		return Credenciales.builder()
		.email(registerRequest.getEmail())
		.contrasenya(passwordEncoder.encode("iesbelen"))
		.id_doctor_duenyo(id)
		.rol(rol)
		.build();
	 }

	public Optional<Credenciales> findCredencialByEmail(String email){
		return credencialesRepository.getCredencialesByEmail(email);
	}


    public AuthResponse login(UserPassword userPassword) {
		  auth.authenticate(new UsernamePasswordAuthenticationToken(userPassword.getEmail(), userPassword.getContrasenya()));
		UserDetails user = credencialesRepository.getCredencialesByEmail(userPassword.getEmail()).orElseThrow();
		String token = jwtService.getToken(user);
		return new AuthResponse(token);
	}

}
