package com.clinicaOdontologica.repository;

import com.clinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}

