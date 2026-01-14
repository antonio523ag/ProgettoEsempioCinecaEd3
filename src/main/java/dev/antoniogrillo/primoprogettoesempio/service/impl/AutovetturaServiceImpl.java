package dev.antoniogrillo.primoprogettoesempio.service.impl;

import dev.antoniogrillo.primoprogettoesempio.dto.request.ModificaAutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobilePaginaDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Autovettura;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.mapper.AutovetturaMapper;
import dev.antoniogrillo.primoprogettoesempio.repository.AutovetturaRepository;
import dev.antoniogrillo.primoprogettoesempio.service.def.AutovetturaService;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutovetturaServiceImpl implements AutovetturaService {

    private final AutovetturaRepository repo;
    private final AutovetturaMapper mapper;
    private final PersonaService personaService;

    @Override
    public List<AutomobileDTO> getPerUtente(Persona persona) {
        List<Autovettura> a=repo.getAllByIdUtente(persona.getId());
        return mapper.toAutomobileDTO(a);
    }

    @Override
    public AutomobilePaginaDTO getPerUtente(Persona persona, int numeroPagina) {
        Sort s=Sort.by("marca").ascending().and(Sort.by("modello").ascending());
        Pageable p= PageRequest.of(numeroPagina,5,s);
        Page<Autovettura> pagine=repo.getAllByProprietario_Id(persona.getId(),p);
        return mapper.toAutomobilePaginaDTO(pagine);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public AutomobileDTO modificaAutomobile(ModificaAutomobileDTO dto) {
        Persona p=personaService.getById(dto.getIdProprietario());
        Autovettura a1=repo.findById(dto.getId()).orElse(null);
        if(p==null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Proprietario non trovato");
        Autovettura a = mapper.toAutovettura(dto,p);
        a=repo.save(a);
        a=repo.findById(a.getId()).orElse(null);
        return mapper.toAutomobileDTO(a);
    }
}
