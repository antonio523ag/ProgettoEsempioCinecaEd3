package dev.antoniogrillo.primoprogettoesempio.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModificaAutomobileDTO {
    private String marca;
    private String modello;
    private int annoImmatricolazione;
    private String descrizione;
    private long id;
    private long idProprietario;
    private int version;
}
