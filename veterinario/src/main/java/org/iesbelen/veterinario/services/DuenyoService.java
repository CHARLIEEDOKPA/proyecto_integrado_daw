package org.iesbelen.veterinario.services;

import java.util.List;
import java.util.Optional;

import org.iesbelen.veterinario.model.Duenyo;
import org.iesbelen.veterinario.repo.DuenyoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DuenyoService {

    @Autowired
    private DuenyoRepository duenyoRepository;


    public Duenyo addDuenyo(Duenyo duenyo){
        return duenyoRepository.save(duenyo);
    }

    public List<Duenyo> getListDuenyo() {
        return duenyoRepository.findAll();
    }

    public boolean deleteDuenyo(long id) {
        Optional<Duenyo> opt = duenyoRepository.findById(id);
        if (opt.isPresent()) {
            duenyoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean modifyDuenyo(Long id, Duenyo duenyo) {
        Optional<Duenyo> opt = duenyoRepository.findById(id);
        if (opt.isPresent()) {
            boolean equals =  id.equals(duenyo.getId());
            if (equals) {
                duenyoRepository.save(duenyo);
            }
            return equals;
        }
        return false;
        
    }

    public Optional<Duenyo> getDuenyoById(long id) {
        return duenyoRepository.findById(id);
    }

}
