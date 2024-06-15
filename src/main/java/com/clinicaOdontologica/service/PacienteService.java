package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService {
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
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }
}
