package dev.antoniogrillo.primoprogettoesempio.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String via;
    private String civico;
    private String cap;
    private String citta;

    @ManyToMany(mappedBy = "indirizzi")
    private List<Persona> persone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public List<Persona> getPersone() {
        return persone;
    }

    public void setPersone(List<Persona> persone) {
        this.persone = persone;
    }
}
