package dev.antoniogrillo.primoprogettoesempio.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginaGenericaDTO<T> {

    private List<T> lista;
    private int numeroPagina;
    private int pagineTotali;
}
