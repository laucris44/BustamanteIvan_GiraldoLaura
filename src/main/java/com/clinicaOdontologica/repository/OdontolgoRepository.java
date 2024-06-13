package repository;

import com.clinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontolgoRepository extends JpaRepository<Odontologo, Long> {
    //Optional<Odontologo> findById(Long id);

    Optional<Odontologo> findByMatricula(String matricula);
}
///para que auxiliea a buscar el odontologo por el ID