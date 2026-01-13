package dev.antoniogrillo.primoprogettoesempio.service.impl;

import dev.antoniogrillo.primoprogettoesempio.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.repository.PersonaRepository;
import dev.antoniogrillo.primoprogettoesempio.service.def.IndirizzoService;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;


@Service
public class PersonaServiceImpl implements PersonaService {

    //fieldInjection (consigliata da Spring 2.1 a 3.0)
//    @Autowired
//    private PersonaRepository repo;

    //Constructor Injection(consigliata da Spring 3.1)
    private final PersonaRepository repo;

    public PersonaServiceImpl(PersonaRepository repo)  {
        this.repo = repo;
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
        try {
            return repo.save(persona);
        }catch (Exception e){
            if(e instanceof SQLIntegrityConstraintViolationException) throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
            else throw e;
        }
    }

    @Override
    public Persona login(LoginRequestDTO request) {
        Optional<Persona> opt=repo.findByEmail(request.getUsername());
        if(!opt.isPresent())return null;
        Persona p=opt.get();
        if(!p.getPassword().equals(request.getPassword()))return null;
        return repo.findByEmailAndPassword(request.getUsername(),request.getPassword())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Credenziali non valide"));
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

    @Override
    public List<Persona> findAllById(List<Long> idPersone) {
        return repo.findAllById(idPersone);
    }
}
