package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;

public interface PersonaService {

    Persona getById(long id);
    Persona save(Persona persona);
    Persona login(String username,String password);
    Persona delete(long id);
    Persona update(Persona persona);

}
