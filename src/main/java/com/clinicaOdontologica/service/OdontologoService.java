package com.clinicaOdontologica.service;

import com.clinicaOdontologica.entity.Odontologo;
import com.clinicaOdontologica.dto.OdontologoDTO;
import com.clinicaOdontologica.entity.Turno;
import com.clinicaOdontologica.repository.IOdontologoRepository;
import com.clinicaOdontologica.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinicaOdontologica.exeptions.BadRequestException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


@Service
public class OdontologoService implements IOdontologoService{
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;
    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }

    public void eliminarOdontologo(Long id){
        IOdontologoRepository.deleteById(id);
    }
    public Odontologo buscarPorId(Long id) {
        return IOdontologoRepository.findById(id);
    }

    public Optional<Turno> buscarPorID(Long id){
        return ITurnoRepository.findById(id);
    }

    public List<Turno> buscarTodos(){
        return ITurnoRepository.findAll();
    }

    private void guardarOdontologo(OdontologoDTO ondontolgoDTO) {
        //Armamos un método para código repetido
        //Transformamos el estudianteDTO a Objeto de tipo Odontologo con el mapper
        //Las propiedades que se llaman igual, las asigna
        Odontologo odontologo = mapper.convertValue(ondontolgoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }

    //Método HTTP: POST
    //odontologo es un objeto, ese objeto es un body --> Request Body
    @Override
    public void crearOdontologo(OdontologoDTO odontologoDTO) {
        //Utilizo el método guardarOdontologo
        guardarOdontologo(odontologoDTO);

    }

    //Método HTTP: GET
    //Por estar buscando por ID se puede usar Request Param o
    //PATH Variable (recomendable)
    @Override
    public OdontologoDTO buscarOdontologo(Long id) throws BadRequestException {
        //Optional nos permite averiguar si el contenido esta nulo o no
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        //Si el odontologo no es nullo
        if (odontologo.isPresent()) {
            //Lo convertimos en un OdontologoDTO
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
            return odontologoDTO;
        } else
            throw new BadRequestException("El ID: " + id + " no existe");
    }

    //Método HTTP: PUT
    @Override
    public void modificarOdontologo(OdontologoDTO odontologoDTO) {
        guardarOdontologo(odontologoDTO);
    }

    //Método HTTP: DELETE
    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    //Método HTTP: GET
    //Devolver una lista
    @Override
    public Collection<OdontologoDTO> listarOdontologos() {

        List<Odontologo> odontologos = odontologoRepository.findAll();
        //Recorremos la lista y, por cada odontologo, llenamos otra lista
        //con odontologosDTO. Recorremos y llenamos
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        //Recorro la colección de odontologos y lleno otra, donde tranformamos
        //al estudianteDTO
        for (Odontologo odontologo : odontologos)
            odontologosDTO.add(mapper.convertValue(odontologo,OdontologoDTO.class));
        return odontologosDTO;
    }

    public Odontologo findByMatricula(String matricula) {
        Odontologo odontologoFind = null;
        List<Odontologo> odontologos = odontologoRepository.findAll();
        for (Odontologo odontologo : odontologos) {
            if (odontologo.getMatricula().equals(matricula)) {
                odontologoFind = odontologo;
            }
        }
        return odontologoFind;
    }

}




