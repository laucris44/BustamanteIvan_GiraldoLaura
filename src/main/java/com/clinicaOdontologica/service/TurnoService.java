package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.Turno;
import repository.TurnoRepository;

public class TurnoService {
    public Turno guardarTurno(Turno turno) {
        return TurnoRepository.save(turno);
        //    <S extends T> S save(S entity);

    }
}
/*
public Paciente guardarPaciente(Paciente paciente){
    return pacienteRepository.save(paciente);
}
