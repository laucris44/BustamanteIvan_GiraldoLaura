package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.OdontologoDTO;
import com.clinicaOdontologica.dto.PacienteDTO;
import com.clinicaOdontologica.dto.TurnoDTO;
import com.clinicaOdontologica.exeptions.BadRequestException;
import com.clinicaOdontologica.exeptions.ResourceNotFoundException;
import com.clinicaOdontologica.exeptions.TurnoNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
public interface ITurnoService {
    public TurnoDTO crearTurno(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException;
    public TurnoDTO buscarTurno(Long id) throws ResourceNotFoundException;
    public TurnoDTO modificarTurno(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException;
    public void eliminarTurno(Long id) throws TurnoNotFoundException, TurnoNotFoundException;
    public List<TurnoDTO> listarTurnos() throws ResourceNotFoundException;
}
