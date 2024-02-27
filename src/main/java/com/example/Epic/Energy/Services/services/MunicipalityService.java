package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Municipality;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MunicipalityService {
    @Autowired
    private MunicipalityRepository municipalityRepository;
    public void save(Municipality municipality){
        municipalityRepository.save(municipality);
    }
    public Optional<Municipality> findById(long id){
        return municipalityRepository.findById(id);
    }
    public Municipality findByName(String name) throws NotFoundException {
        Optional<Municipality> optionalMunicipality= municipalityRepository.findByName(name);
        if (optionalMunicipality.isEmpty()) throw new NotFoundException("Municipality not found");
        return optionalMunicipality.get();
    }
}
