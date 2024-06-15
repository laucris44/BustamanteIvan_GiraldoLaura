package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.PacienteDTO;
import com.clinicaOdontologica.exeptions.BadRequestException;
import com.clinicaOdontologica.exeptions.ResourceNotFoundException;

import java.util.Collection;
import java.util.List;
public interface IPacienteService {
    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO);
    public PacienteDTO buscarPaciente(Long id) throws BadRequestException, ResourceNotFoundException;
    public void modificarPaciente(PacienteDTO pacienteDTO);
    public void eliminarPaciente(Long id);
    public List<PacienteDTO> listarPacientes();
}
