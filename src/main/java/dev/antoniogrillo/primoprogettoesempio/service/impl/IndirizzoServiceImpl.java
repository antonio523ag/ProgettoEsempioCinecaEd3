package dev.antoniogrillo.primoprogettoesempio.service.impl;

import dev.antoniogrillo.primoprogettoesempio.dto.request.AggiungiIndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.IndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Indirizzo;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.mapper.IndirizzoMapper;
import dev.antoniogrillo.primoprogettoesempio.repository.IndirizzoRepository;
import dev.antoniogrillo.primoprogettoesempio.service.def.IndirizzoService;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndirizzoServiceImpl implements IndirizzoService {

    private final IndirizzoRepository repo;
    private final IndirizzoMapper mapper;
    private final PersonaService personaService;

    public IndirizzoServiceImpl(IndirizzoRepository repo, IndirizzoMapper mapper, PersonaService personaService) {
        this.repo = repo;
        this.mapper = mapper;
        this.personaService = personaService;
    }

    @Override
    public IndirizzoDTO getById(long id) {
        Indirizzo i=repo.findById(id).orElse(null);
        if(i==null)return null;
        return mapper.toIndirizzoDTO(i);
    }

    @Override
    public boolean aggiungi(AggiungiIndirizzoDTO dto, Persona p) {
        Indirizzo i=mapper.toIndirizzo(dto);
        Indirizzo check=repo.findByViaAndCivicoAndCapAndCitta(i.getVia(),i.getCivico(),i.getCap(),i.getCitta());
        if(check!=null)i=check;
        if(i.getPersone()==null)i.setPersone(new ArrayList<>());
        i.getPersone().add(p);
        repo.save(i);
        return true;
    }
}
