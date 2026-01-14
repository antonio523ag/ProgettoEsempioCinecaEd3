package dev.antoniogrillo.primoprogettoesempio.service.def;

import dev.antoniogrillo.primoprogettoesempio.dto.request.ModificaAutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobilePaginaDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Autovettura;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutovetturaService {


    List<AutomobileDTO> getPerUtente(Persona persona);
    AutomobilePaginaDTO getPerUtente(Persona persona, int numeroPagina);

    AutomobileDTO modificaAutomobile(ModificaAutomobileDTO dto);
}
