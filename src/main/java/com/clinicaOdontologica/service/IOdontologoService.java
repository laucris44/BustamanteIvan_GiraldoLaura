package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.OdontologoDTO;
import com.clinicaOdontologica.exeptions.BadRequestException;

import java.util.Collection;
public interface IOdontologoService {
    public void crearOdontologo(OdontologoDTO odontologoDTO);
    public OdontologoDTO buscarOdontologo(Long id) throws BadRequestException;
    public void modificarOdontologo(OdontologoDTO odontologoDTO);
    public void eliminarOdontologo(Long id);
    public Collection<OdontologoDTO> listarOdontologos();
}
