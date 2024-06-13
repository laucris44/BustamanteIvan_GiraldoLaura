package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.Odontologo;
import com.clinicaOdontologica.entity.Turno;
import com.clinicaOdontologica.repository.OdontolgoRepository;
import com.clinicaOdontologica.repository.OdontologoRepository;
import com.clinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void eliminarOdontologo(Long id){
        OdontolgoRepository.deleteById(id);
    }
    public Odontologo buscarPorId(Long id) {
        return OdontolgoRepository.findById(id);
    }

        /*
buscar por id
registrar nuevo
actulizar
elminar
listar todos
     */

    public void eliminarTurno(Long id){
        TurnoRepository.findById(id);
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
