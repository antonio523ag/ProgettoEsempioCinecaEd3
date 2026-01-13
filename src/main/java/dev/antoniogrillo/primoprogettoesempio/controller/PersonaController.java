package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.service.def.GestoreTokenService;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    private final PersonaService service;
    private final GestoreTokenService gestoreTokenService;

    public PersonaController(PersonaService service, GestoreTokenService gestoreTokenService) {
        this.service = service;
        this.gestoreTokenService = gestoreTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<Persona> login(@RequestBody LoginRequestDTO request){
        Persona p= service.login(request);
        if(p==null) return ResponseEntity.notFound().build();
        String token = gestoreTokenService.generaToken(p);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization",token).body(p);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Persona> getById(@PathVariable long id){
        Persona p= service.getById(id);
        if(p==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/salva")
    public ResponseEntity<Persona> salva(@RequestBody Persona persona){
        Persona p= service.save(persona);
        if(p==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else return ResponseEntity.status(HttpStatus.CREATED).body(p);
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
