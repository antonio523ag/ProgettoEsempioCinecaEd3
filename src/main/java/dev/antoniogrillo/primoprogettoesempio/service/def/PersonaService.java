package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;

import java.util.List;

public interface PersonaService {

    Persona getById(long id);
    Persona save(Persona persona);
    Persona login(LoginRequestDTO request);
    Persona delete(long id);
    Persona update(Persona persona);

    List<Persona> findAllById(List<Long> idPersone);
}
