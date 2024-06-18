package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.PacienteDTO;
import com.clinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.exeptions.BadRequestException;
import com.clinicaOdontologica.exeptions.ResourceNotFoundException;
import com.clinicaOdontologica.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IPacienteService{
    @Autowired
    private IPacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPorID(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

    @Override
    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO) {
        return null;
    }

    @Override
    public PacienteDTO buscarPaciente(Long id) throws BadRequestException, ResourceNotFoundException {
        return null;
    }

    @Override
    public void modificarPaciente(PacienteDTO pacienteDTO) {

    }

    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

    @Override
    public List<PacienteDTO> listarPacientes() {
        return List.of();
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }
}
