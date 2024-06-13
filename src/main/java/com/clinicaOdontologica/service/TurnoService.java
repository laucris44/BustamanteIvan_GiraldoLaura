package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.entity.Turno;
import com.clinicaOdontologica.repository.TurnoRepository;

import java.util.List;
import java.util.Optional;

public class TurnoService {
    public Turno guardarTurno(Turno turno) {
        return TurnoRepository.save(turno);
        //    <S extends T> S save(S entity);
    }

    public void actualizarTurno(Turno turno){
        TurnoRepository.save(turno);
    }
    /*
    guardar
    elminar
    acualizar
    buscar listar
     */

    public void eliminarTurno(Long id){
        TurnoRepository.deleteById(id);
    }

    public Optional<Turno> buscarPorID(Long id){
        return TurnoRepository.findById(id);
    }

    public List<Turno> buscarTodos(){
        return TurnoRepository.findAll();
    }









}
/*
public Paciente guardarPaciente(Paciente paciente){
    return pacienteRepository.save(paciente);
}
