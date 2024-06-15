package com.clinicaOdontologica.repository;

import com.clinicaOdontologica.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
