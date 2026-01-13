package dev.antoniogrillo.primoprogettoesempio.service.impl;

import dev.antoniogrillo.primoprogettoesempio.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;

public class PersonaServiceImpl2 implements PersonaService {
    @Override
    public Persona getById(long id) {
        return null;
    }

    @Override
    public Persona save(Persona persona) {
        return null;
    }

    @Override
    public Persona login(LoginRequestDTO request) {
        return null;
    }


    @Override
    public Persona delete(long id) {
        return null;
    }

    @Override
    public Persona update(Persona persona) {
        return null;
    }

    @Override
    public List<Persona> findAllById(List<Long> idPersone) {
        return List.of();
    }
}
