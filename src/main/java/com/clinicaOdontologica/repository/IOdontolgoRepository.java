package com.clinicaOdontologica.repository;

import com.clinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IOdontolgoRepository extends JpaRepository<Odontologo, Long> {
    //Optional<Odontologo> findById(Long id);

    Optional<Odontologo> findByMatricula(String matricula);
}
