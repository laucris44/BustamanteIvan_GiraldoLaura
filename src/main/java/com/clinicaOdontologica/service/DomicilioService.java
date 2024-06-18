package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.Domicilio;
import com.clinicaOdontologica.exeptions.BadRequestException;
import com.clinicaOdontologica.repository.IDomicilioRepository;
import com.clinicaOdontologica.dto.DomicilioDTO;
import com.clinicaOdontologica.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class DomicilioService implements IDomicilioService{
    @Autowired
    private IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    private void guardarDomicilio(DomicilioDTO domicilioDTO) {
        Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
        domicilioRepository.save(domicilio);
    }


    @Override
    public void crearDomicilio(DomicilioDTO domicilioDTO) {
        guardarDomicilio(domicilioDTO);
    }

    @Override
    public DomicilioDTO buscarDomicilio(Long id) throws BadRequestException {
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        DomicilioDTO domicilioDTO = null;
        if (domicilio.isPresent()) {
            domicilioDTO = mapper.convertValue(domicilio, DomicilioDTO.class);
            return domicilioDTO;
        } else
            throw new BadRequestException("El ID: " + id + " no existe");
    }

    @Override
    public void modificarDomicilio(DomicilioDTO domicilioDTO) {
        guardarDomicilio(domicilioDTO);
    }

    @Override
    public void eliminarDomicilio(Long id) {
        domicilioRepository.deleteById(id);
    }

    @Override
    public Set<DomicilioDTO> listarDomicilios() {
        List<Domicilio> domicilios = domicilioRepository.findAll();

        Set<DomicilioDTO> domiciliosDTO = new HashSet<>();
        for (Domicilio domicilio: domicilios) {
            domiciliosDTO.add(mapper.convertValue(domicilio, DomicilioDTO.class));
        }
        return domiciliosDTO;
    }

}
