package com.clinicaOdontologica.repository;

import com.clinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;
import java.util.Set;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByEmail(String email);
    @Query("SELECT p FROM Paciente p JOIN FETCH p.domicilio WHERE p.id = :id")
    Paciente findByIdWithDomicilio(@Param("id") Long id);

    Set<Paciente> findByDni(String dni);
}
