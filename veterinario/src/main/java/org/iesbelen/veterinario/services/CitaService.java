package org.iesbelen.veterinario.services;

import java.util.List;
import java.util.Optional;

import org.iesbelen.veterinario.model.Cita;
import org.iesbelen.veterinario.model.CitaRequest;
import org.iesbelen.veterinario.model.Doctor;
import org.iesbelen.veterinario.repo.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService {


    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public List<Cita> getCitasByIdMascota(long id) {
        return citaRepository.getCitasByIdMascota(id);
    }

    public List<Cita> getCitasByIdDoctor(long id) {
        return citaRepository.getCitasByIdDoctor(id);
    }

    public Optional<Cita> getCitaById(long id) {
        return citaRepository.findById(id);
    }

    synchronized public Cita saveCita(Cita cita){
        return citaRepository.save(cita);
    }

    public boolean editCita(Long id,Cita cita) {
        Optional<Cita> opt = citaRepository.findById(id);
        if (opt.isPresent()) {
            boolean equals = id.equals(cita.getId());
            if (equals) {
                citaRepository.save(cita);
            }
            return equals;
        }
        return false;
    }

    public boolean removeCita(long id) {
        Optional<Cita> opt =  citaRepository.findById(id);
        if (opt.isPresent()) {
            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean citaRequestCorrect(CitaRequest citaRequest) {
        if (citaRequest.getTime() == null) {
            return false;
        }

        if (citaRequest.getDate() == null) {
            return false;
        }

        if (citaRequest.getId_mascota() == 0L) {
            return false;
        }
        
        return true;
    }
    
}
