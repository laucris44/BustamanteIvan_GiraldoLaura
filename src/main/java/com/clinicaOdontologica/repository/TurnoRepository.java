package repository;

import com.clinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno,Long>{
    Optional<Turno> findById(Long id);
}

//
