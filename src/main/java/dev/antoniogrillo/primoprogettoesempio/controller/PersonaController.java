package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    private final PersonaService service;

    public PersonaController(PersonaService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Persona login(@RequestParam String username,@RequestParam String password){
        return service.login(username, password);
    }

    @GetMapping("/{id}")
    public Persona getById(@PathVariable long id){
        return service.getById(id);
    }

    @PostMapping("/salva")
    public Persona salva(@RequestBody Persona persona){
        return service.save(persona);
    }

    @DeleteMapping("/{id}")
    public Persona elimina(@PathVariable long id){
        return service.delete(id);
    }

    @PutMapping("/aggiorna")
    public Persona aggiorna(@RequestBody Persona persona){
        return service.update(persona);
    }


}
