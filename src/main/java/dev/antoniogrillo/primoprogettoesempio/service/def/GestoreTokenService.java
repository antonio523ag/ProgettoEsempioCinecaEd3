package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;

public interface GestoreTokenService {
    String generaToken(Persona persona);
    boolean verificaToken(String token);
    Persona getPersonaByToken(String token);
}
