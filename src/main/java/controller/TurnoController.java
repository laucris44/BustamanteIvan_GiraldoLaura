package controller;

import entity.Turno;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController() {
        turnoService= new TurnoService();
    }
    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){

        PacienteService pacienteService= new PacienteService();
        OdontologoService odontologoService= new OdontologoService();
        if(pacienteService.buscarPaciente(turno.getPaciente().getId())!=null&&odontologoService.buscarPorId(turno.getOdontologo().getId())!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            //bad request or not found
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosLosTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

}
