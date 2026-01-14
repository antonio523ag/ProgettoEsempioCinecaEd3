package dev.antoniogrillo.primoprogettoesempio.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutomobileDTO {
    private long id;
    private String marca;
    private String modello;
    private int annoImmatricolazione;
    private String descrizione;
    private int version;
}
