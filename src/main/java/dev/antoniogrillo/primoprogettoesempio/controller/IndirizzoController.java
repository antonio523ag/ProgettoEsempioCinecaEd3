package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.dto.request.AggiungiIndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.IndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.service.def.IndirizzoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor //crea un costruttore con tutte i parametri final
public class IndirizzoController {

    private final IndirizzoService service;


    @GetMapping("/indirizzi/{id}")
    public ResponseEntity<IndirizzoDTO> getById(@PathVariable long id){
        IndirizzoDTO dto = service.getById(id);
        if(dto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/indirizzo/add")
    public ResponseEntity<Void> add(@RequestBody AggiungiIndirizzoDTO dto, @AuthenticationPrincipal Persona p){
        boolean riuscito=service.aggiungi(dto,p);
        if(riuscito)return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }

}
