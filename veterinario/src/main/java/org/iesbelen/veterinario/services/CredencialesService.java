package org.iesbelen.veterinario.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.model.Doctor;
import org.iesbelen.veterinario.model.Duenyo;
import org.iesbelen.veterinario.model.UserPassword;
import org.iesbelen.veterinario.repo.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredencialesService {
    
    @Autowired
    private CredencialesRepository credencialesRepository;

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

	public Optional<Credenciales> findCredencialByEmail(UserPassword userPassword){
		return credencialesRepository.getCredencialesByEmail(userPassword.getEmail());
	}

    public boolean loginCorrect(UserPassword userPassword) {
        Optional<Credenciales> opt = credencialesRepository.getCredencialesByEmail(userPassword.getEmail());
        return opt.isPresent() && opt.get().getContrasenya().
        equals(userPassword.getContrasenya());
    }

    public static String hashPassword(String password ) throws NoSuchAlgorithmException {
		MessageDigest digest;
		
		digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
				password.getBytes(StandardCharsets.UTF_8));
		
		return bytesToHex(encodedhash);					
	}
	
	private static String bytesToHex(byte[] byteHash) {
		
	    StringBuilder hexString = new StringBuilder(2 * byteHash.length);	  	
	    for (int i = 0; i < byteHash.length; i++) {
	        String hex = Integer.toHexString(0xff & byteHash[i]);
	        if (hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    
	    return hexString.toString();
	}

}
