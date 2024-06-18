package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.dto.OdontologoDTO;
import com.clinicaOdontologica.dto.PacienteDTO;
import com.clinicaOdontologica.dto.TurnoDTO;
import com.clinicaOdontologica.entity.Odontologo;
import com.clinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.entity.Turno;
import com.clinicaOdontologica.exeptions.BadRequestException;
import com.clinicaOdontologica.exeptions.ResourceNotFoundException;
import com.clinicaOdontologica.exeptions.TurnoNotFoundException;
import com.clinicaOdontologica.repository.IOdontologoRepository;
import com.clinicaOdontologica.repository.IPacienteRepository;
import com.clinicaOdontologica.repository.ITurnoRepository;
import com.clinicaOdontologica.service.IOdontologoService;
import com.clinicaOdontologica.service.IPacienteService;
import com.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TurnoService implements ITurnoService{

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IOdontologoService odontologoService;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;
    public TurnoDTO guardarTurno (TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        if (turnoDTO.getPaciente() == null || turnoDTO.getOdontologo() == null || turnoDTO.getOdontologo().getId() == null) {
            throw new BadRequestException("Verifique los datos ingresados");
        }

        PacienteDTO pacienteDTO = turnoDTO.getPaciente();
        Paciente paciente = pacienteRepository.findById(pacienteDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el paciente con id " + pacienteDTO.getId()));

        OdontologoDTO odontologoDTO = turnoDTO.getOdontologo();
        Odontologo odontologo = odontologoRepository.findById(odontologoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el odontólogo con id " + odontologoDTO.getId()));

        if (!disponibilidadTurno(odontologo.getId(), turnoDTO.getFecha(), turnoDTO.getHora())) {
            throw new ResourceNotFoundException("El odontólogo ya tiene un turno asignado");
        }

        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        Turno savedTurno = turnoRepository.save(turno);

        TurnoDTO response = mapper.convertValue(savedTurno, TurnoDTO.class);
        response.setPaciente(pacienteDTO);
        response.setOdontologo(odontologoDTO);
        return response;
    }

    private Boolean disponibilidadTurno(Long idOdontologo, LocalDate fecha, LocalTime hora) throws ResourceNotFoundException {
        boolean response = true;
        List<Turno> listaTurnos = turnoRepository.findAll();
        for (Turno turno : listaTurnos) {
            TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            if (turnoDTO.getOdontologo() != null && turnoDTO.getOdontologo().getId().equals(idOdontologo) && turnoDTO.getFecha().equals(fecha) && turnoDTO.getHora().equals(hora)) {
                response = false;
            }
        }
        return response;
    }

    @Override
    public TurnoDTO crearTurno(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        guardarTurno(turnoDTO);
        return turnoDTO;
    }

    @Override
    public TurnoDTO buscarTurno(Long id) throws ResourceNotFoundException {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El turno no existe"));
        TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
        PacienteDTO pacienteDTO = mapper.convertValue(turno, PacienteDTO.class);
        OdontologoDTO odontologoDTO = mapper.convertValue(turno, OdontologoDTO.class);
        turnoDTO.setPaciente(pacienteDTO);
        turnoDTO.setOdontologo(odontologoDTO);
        return turnoDTO;
    }

    @Override
    public TurnoDTO modificarTurno(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        // Buscar el turno actual en la base de datos
        Turno turnoActual = turnoRepository.findById(turnoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el turno con id " + turnoDTO.getId()));

        // Verificar si la fecha y la hora son distintas
        if (!turnoActual.getFecha().equals(turnoDTO.getFecha()) || !turnoActual.getHora().equals(turnoDTO.getHora())) {
            // Verificar la disponibilidad del odontólogo en la nueva fecha y hora
            if (!disponibilidadTurno(turnoDTO.getOdontologo().getId(), turnoDTO.getFecha(), turnoDTO.getHora())) {
                throw new BadRequestException("El odontólogo ya tiene un turno asignado en la nueva fecha y hora");
            }
        }

        guardarTurno(turnoDTO);
        return turnoDTO;
    }

    @Override
    public void eliminarTurno(Long id) throws TurnoNotFoundException {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);

        if (turnoOptional.isPresent()) {
            turnoRepository.deleteById(id);
        } else {
            throw new TurnoNotFoundException("El turno no existe");
        }
    }

    @Override
    public List<TurnoDTO> listarTurnos() throws ResourceNotFoundException {
        List<Turno> turnos = turnoRepository.findAll();
        if (turnos.size() <= 0) {
            throw new ResourceNotFoundException("No hay turnos cargados");
        }
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for (Turno turno : turnos) {
            TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            PacienteDTO pacienteDTO = mapper.convertValue(turno.getPaciente(), PacienteDTO.class);
            OdontologoDTO odontologoDTO = mapper.convertValue(turno.getOdontologo(), OdontologoDTO.class);
            turnoDTO.setPaciente(pacienteDTO);
            turnoDTO.setOdontologo(odontologoDTO);
            turnosDTO.add(turnoDTO);
        }
        return turnosDTO;
    }

}
