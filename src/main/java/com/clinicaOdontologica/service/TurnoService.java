package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.Turno;
import com.clinicaOdontologica.repository.ITurnoRepository;

import java.util.List;
import java.util.Optional;

public class TurnoService {
    public Turno guardarTurno(Turno turno) {
        return ITurnoRepository.save(turno);
        //    <S extends T> S save(S entity);
    }

    public void actualizarTurno(Turno turno){
        ITurnoRepository.save(turno);
    }
    /*
    guardar
    elminar
    acualizar
    buscar listar
     */

    public void eliminarTurno(Long id){
        ITurnoRepository.deleteById(id);
    }

    public Optional<Turno> buscarPorID(Long id){
        return ITurnoRepository.findById(id);
    }

    public List<Turno> buscarTodos(){
        return ITurnoRepository.findAll();
    }









}
/*
public Paciente guardarPaciente(Paciente paciente){
    return pacienteRepository.save(paciente);
}
