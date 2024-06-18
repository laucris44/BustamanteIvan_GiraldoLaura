package com.clinicaOdontologica.security;

import com.clinicaOdontologica.security.filter.JwtRequestFilter;
import com.clinicaOdontologica.service.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public class SecurityConfig {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).hasRole("MODERATOR")
                .requestMatchers("/css/**", "img/**", "/js/**").permitAll()
                .requestMatchers("/login.html").permitAll()
                .requestMatchers("/odontologoList.html", "/pacienteList.html","/turnoList.html","/odontologo/listar", "/paciente/listar", "/turno/listar", "/index.html").hasAnyRole("USER","ADMIN", "MODERATOR")
                .requestMatchers("/odontologo/**", "/paciente/**", "/turno/**", "/odontologoAlta.html", "/pacienteAlta.html", "/turnoAlta.html").hasAnyRole("ADMIN", "MODERATOR")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/accesoDenegado.html");
        //  .and()
        //     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}