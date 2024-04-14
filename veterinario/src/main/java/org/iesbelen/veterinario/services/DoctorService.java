package org.iesbelen.veterinario.services;

import java.util.List;
import java.util.Optional;

import org.iesbelen.veterinario.model.Doctor;
import org.iesbelen.veterinario.model.RegisterRequest;
import org.iesbelen.veterinario.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;


    public Optional<Doctor> getDoctorById(long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getListDoctores() {
        return doctorRepository.findAll();
    }

    public boolean modifyDoctor(Long id, Doctor doctor) {
        Optional<Doctor> opt = doctorRepository.findById(id);
        if (opt.isPresent()) {
            boolean equals = id.equals(doctor.getId());
            if (equals) {
                doctorRepository.save(doctor);
            }
            return equals;
        }
        return false;
    }

    public boolean deleteDoctor(long id) {
        Optional<Doctor> opt =  doctorRepository.findById(id);
        if (opt.isPresent()) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
        
    }

    public Doctor buildDoctor(RegisterRequest registerRequest) {
        return Doctor.builder().activo(true)
        .nombre(registerRequest.getNombre())
        .apellidos1(registerRequest.getApellidos1())
        .apellidos2(registerRequest.getApellidos2())
        .email(registerRequest.getEmail())
        .nacimiento(registerRequest.getNacimiento())
        .residencia(registerRequest.getResidencia())
        .telefono(registerRequest.getTelefono())
        .build();
    } 
}
