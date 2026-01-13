package dev.antoniogrillo.primoprogettoesempio.controller;

import dev.antoniogrillo.primoprogettoesempio.entity.Autovettura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class PrimoController {

    @Autowired
    private ObjectMapper mapper;

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "ciao a tutti";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/sayHello")
    public String sayHelloPost(){
        return "ciao a tutti ma in Post";
    }

    @GetMapping("/metodo1")
    public String metodo1(){
        return "metodo1";
    }

    @PostMapping("/metodo2")
    public String metodo2(){
        return "metodo2";
    }

    //http://localhost:8080/api/provaUri/Antonio/Grillo/altro
    @GetMapping("/provaUri/{nome}/{surname}/altro")
    public String testUriParameter(@PathVariable String nome,@PathVariable("surname") String cognome){
        //System.out.println(nome+" "+cognome); //Antonio;XXX perde XXX
        //String[] split=nome.split(";");
        return nome+" "+cognome;
    }

    //http://localhost:8080/api/provaParam?nome=Antonio&surname=Grillo
    @GetMapping("/provaParam")
    public String testParam(@RequestParam String nome,@RequestParam("surname") String cognome){
        return nome+" "+cognome;
    }

    @GetMapping(value="/getAutovettura/{marca}/{modello}/{annoImmatricolazione}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Autovettura getAutovettura(@PathVariable String marca,@PathVariable String modello,@PathVariable int annoImmatricolazione){
        return new Autovettura(marca,modello,annoImmatricolazione);
    }

    @PostMapping(value="/getAutovettura",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getAutovettura(@RequestBody Autovettura a){
        return "Marca: "+a.getMarca()+" Modello: "+a.getModello()+" Anno: "+a.getAnnoImmatricolazione();
    }

    @GetMapping(value="/getXML",produces = MediaType.APPLICATION_XML_VALUE)
    public Autovettura getXML(){
        return new Autovettura("Fiat","Panda",1990);
    }

}
