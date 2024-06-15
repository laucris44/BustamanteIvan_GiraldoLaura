package com.clinicaOdontologica.repository;

import com.clinicaOdontologica.dto.DomicilioDTO;
import com.clinicaOdontologica.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {

    @Query("FROM Domicilio domicilio WHERE domicilio.id = 1")
    Optional<DomicilioDTO> buscarDomicilio(@Param("id") Long id);


}