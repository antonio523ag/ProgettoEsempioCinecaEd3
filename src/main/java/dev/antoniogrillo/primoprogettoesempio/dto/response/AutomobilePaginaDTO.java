package dev.antoniogrillo.primoprogettoesempio.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AutomobilePaginaDTO {
    private List<AutomobileDTO> automobili;
    private int numeroPagina;
    private int pagineTotali;
}
