package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.dto.request.ModificaAutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobilePaginaDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.service.def.AutovetturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AutomobileController {

    private final AutovetturaService service;

    @GetMapping("/api/automobili/get/page/{numeroPagina}")
    public ResponseEntity<AutomobilePaginaDTO> getAutomobili(@AuthenticationPrincipal Persona persona, @PathVariable int numeroPagina){
        if(numeroPagina>0)numeroPagina--;
        //List<AutomobileDTO> automobili=service.getPerUtente(persona);
        AutomobilePaginaDTO a=service.getPerUtente(persona,numeroPagina);
        return ResponseEntity.ok(a);
    }

    @GetMapping("/api/automobili/get")
    public ResponseEntity<AutomobilePaginaDTO> getAutomobili(@AuthenticationPrincipal Persona persona){
        return getAutomobili(persona,0);
    }

    @PutMapping("/api/automobili")
    public ResponseEntity<AutomobileDTO> modificaAutomobile(@RequestBody ModificaAutomobileDTO dto){
        AutomobileDTO dtoModificato=service.modificaAutomobile(dto);
        return ResponseEntity.ok(dtoModificato);
    }


}
