package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.dto.request.AggiungiIndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.IndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;

public interface IndirizzoService {


    IndirizzoDTO getById(long id);

    boolean aggiungi(AggiungiIndirizzoDTO dto, Persona p);
}
