package dev.antoniogrillo.primoprogettoesempio.dto.response;

import java.util.List;

public class IndirizzoDTO {
    private String via;
    private String civico;
    private String cap;
    private String citta;
    private long id;
    private List<String> nomeAbitanti;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getNomeAbitanti() {
        return nomeAbitanti;
    }

    public void setNomeAbitanti(List<String> nomeAbitanti) {
        this.nomeAbitanti = nomeAbitanti;
    }
}
