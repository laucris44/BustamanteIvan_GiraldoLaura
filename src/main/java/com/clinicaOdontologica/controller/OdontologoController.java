package com.clinicaOdontologica.controller;


import com.clinicaOdontologica.dto.OdontologoDTO;
import com.clinicaOdontologica.exeptions.BadRequestException;
import com.clinicaOdontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private IOdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<?> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws BadRequestException {
        try {
            odontologoService.crearOdontologo(odontologoDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("No se pudo crear el odontologo");
        }
    }

    @GetMapping("/{id}")
    public OdontologoDTO buscarOdontologoId(@PathVariable Long id) throws Exception {
        return odontologoService.buscarOdontologo(id);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.modificarOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDomicilio(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        odontologoService.eliminarOdontologo(id);
        response = ResponseEntity.status(HttpStatus.OK).body("Odontologo eliminado");
        return response;
    }


    @GetMapping("/listar")
    public Collection<OdontologoDTO> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

}
