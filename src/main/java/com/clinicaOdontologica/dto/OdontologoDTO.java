package com.clinicaOdontologica.dto;

import lombok.Getter;
import java.util.Set;
@Getter
public class OdontologoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    private Set<TurnoDTO> turnos;

    public OdontologoDTO() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
