package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.DomicilioDTO;
import com.clinicaOdontologica.exeptions.BadRequestException;
import org.springframework.context.annotation.Bean;
import java.util.Set;

public interface IDomicilioService {
    public void crearDomicilio(DomicilioDTO domicilioDTO);
    public DomicilioDTO buscarDomicilio(Long id) throws BadRequestException;
    public void modificarDomicilio(DomicilioDTO domicilioDTO);
    public void eliminarDomicilio(Long id);
    public Set<DomicilioDTO> listarDomicilios();
}
