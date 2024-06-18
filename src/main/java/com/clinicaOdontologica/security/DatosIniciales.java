package com.clinicaOdontologica.security;

import com.clinicaOdontologica.entity.User;
import com.clinicaOdontologica.entity.enums.ERole;
import com.clinicaOdontologica.repository.IUserRepository;
import com.clinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
@Autowired
private IUserRepository usuarioRepository;
@Autowired
private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado= passwordEncoder.encode(passSinCifrar);
        User usuario= new User("jorgito", passCifrado, ERole.ROLE_USER,"admin@admin.com","jpereyradh");
        System.out.println("pass cifrado: "+passCifrado);
        usuarioRepository.save(usuario);

    }
}
