package service;

import entity.Paciente;
import entity.Turno;
import repository.TurnoRepository;

import java.util.Optional;

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
