package dev.antoniogrillo.primoprogettoesempio.mapper;

import dev.antoniogrillo.primoprogettoesempio.dto.response.PaginaGenericaDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {

    public<T> PaginaGenericaDTO<T> toPaginaGenericaDTO(Page<T> page){
        PaginaGenericaDTO<T> dto = new PaginaGenericaDTO<>();
        dto.setLista(page.getContent());
        dto.setNumeroPagina(page.getNumber()+1);
        dto.setPagineTotali(page.getTotalPages());
        return dto;
    }
}
