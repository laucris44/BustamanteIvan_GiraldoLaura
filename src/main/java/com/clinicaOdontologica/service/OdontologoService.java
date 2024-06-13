package com.clinicaOdontologica.service;

import entity.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }
}


/*
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
    */

}
