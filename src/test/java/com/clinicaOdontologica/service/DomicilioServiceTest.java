package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.DomicilioDTO;
import com.clinicaOdontologica.service.impl.DomicilioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DomicilioServiceTest {

    @Autowired
    DomicilioService domicilioService;

    @Test
    public void testCrearDomicilio() throws Exception {

        DomicilioDTO domicilioDTO = new DomicilioDTO();
        domicilioDTO.setCalle("Maipú");
        domicilioDTO.setNumero("1523");
        domicilioDTO.setLocalidad("Avellaneda");
        domicilioDTO.setProvincia("Buenos Aires");

        domicilioService.crearDomicilio(domicilioDTO);
        DomicilioDTO buscarDomicilio = domicilioService.buscarDomicilio(1L);


        assertTrue(buscarDomicilio != null);

    }
}