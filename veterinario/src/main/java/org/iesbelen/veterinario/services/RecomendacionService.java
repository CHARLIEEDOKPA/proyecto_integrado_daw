package org.iesbelen.veterinario.services;

import org.iesbelen.veterinario.model.Credenciales;
import org.iesbelen.veterinario.model.Recomendacion;
import org.iesbelen.veterinario.model.RecomendacionRequest;
import org.iesbelen.veterinario.repo.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class RecomendacionService {
    
    @Autowired
    private RecomendacionRepository recomendacionRepository;


    synchronized public Recomendacion saveRecomendación(RecomendacionRequest recomendacionRequest, Credenciales credenciales) {

        Recomendacion recomendacion =  new Recomendacion();
        recomendacion.setId_doctor(credenciales.getId_doctor_duenyo());
        recomendacion.setSobre(recomendacionRequest.getSobre());
        recomendacion.setTexto(recomendacionRequest.getTexto());
        recomendacion.setId_mascota(recomendacionRequest.getId_mascota());
        Recomendacion newRecomendacion;
        try {
            newRecomendacion = recomendacionRepository.save(recomendacion);
            
        } catch (DataIntegrityViolationException e) {
            return null;
        }
        
        return newRecomendacion;

    }

}
