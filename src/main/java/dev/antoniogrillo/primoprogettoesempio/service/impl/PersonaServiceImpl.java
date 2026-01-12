package dev.antoniogrillo.primoprogettoesempio.service.impl;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.repository.PersonaRepository;
import dev.antoniogrillo.primoprogettoesempio.service.def.IndirizzoService;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonaServiceImpl implements PersonaService {

    //fieldInjection (consigliata da Spring 2.1 a 3.0)
//    @Autowired
//    private PersonaRepository repo;

    //Constructor Injection(consigliata da Spring 3.1)
    private final PersonaRepository repo;
    private final IndirizzoService indirizzoService;

    public PersonaServiceImpl(PersonaRepository repo,IndirizzoService i)  {
        this.repo = repo;
        indirizzoService=i;
    }

    @Override
    public Persona getById(long id) {
//        Optional<Persona> opt= repo.findById(id);
//        if(opt.isPresent()) return opt.get();
//        return null;
        return repo.findById(id).orElse(null);
    }

    @Override
    public Persona save(Persona persona) {
        if(persona.getId()!=0)return null;
        return repo.save(persona);
    }

    @Override
    public Persona login(String username, String password) {
        return repo.findAll().stream()
                .filter(p->p.getEmail().equalsIgnoreCase(username)&&p.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    @Override
    public Persona delete(long id) {
        Persona persona = getById(id);
        if(persona!=null)repo.delete(persona);
        return persona;

        //repo.deleteById(id);
    }

    @Override
    public Persona update(Persona persona) {
        if(persona.getId()!=0)return repo.save(persona);
        return null;
    }
}
