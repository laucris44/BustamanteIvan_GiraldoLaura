package com.clinicaOdontologica.service.security.service;

import com.clinicaOdontologica.entity.User;
import com.clinicaOdontologica.repository.IUserRepository;
import com.clinicaOdontologica.service.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
public class UserService implements IUserService {
    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usuarioBuscado = userRepository.findByUsername(username);

        if(usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("El username ingresado no existe en la base de datos. Error.");
        }
    }
}
