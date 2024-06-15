package com.clinicaOdontologica.exeptions;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(String id) {
        super("No se encontró al paciente con id " + id);
    }
}
