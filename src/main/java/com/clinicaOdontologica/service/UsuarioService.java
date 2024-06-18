package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.User;
import com.clinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private IUserRepository usuarioRepository;
    //aca deberia venir la autenticacion
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usuarioBuscado = usuarioRepository.findByUsername(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("No existe el usuario: " + username);
        }

    }



}
