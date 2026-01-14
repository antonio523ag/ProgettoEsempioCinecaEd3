package dev.antoniogrillo.primoprogettoesempio.mapper;

import dev.antoniogrillo.primoprogettoesempio.dto.request.ModificaAutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.AutomobilePaginaDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.PaginaGenericaDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Autovettura;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AutovetturaMapper {

    private final PageMapper pageMapper;

    public AutomobileDTO toAutomobileDTO(Autovettura a){
        AutomobileDTO dto = new AutomobileDTO();
        dto.setMarca(a.getMarca());
        dto.setModello(a.getModello());
        dto.setAnnoImmatricolazione(a.getAnnoImmatricolazione());
        dto.setId(a.getId());
        dto.setDescrizione(a.getDescrizione());
        dto.setVersion(a.getVersion());
        return dto;
    }

    public List<AutomobileDTO> toAutomobileDTO(List<Autovettura> a){
        return a==null?new ArrayList<>():a.stream().map(this::toAutomobileDTO).toList();
    }

    public AutomobilePaginaDTO toAutomobilePaginaDTO(Page<Autovettura> a){
        AutomobilePaginaDTO dto = new AutomobilePaginaDTO();
        dto.setAutomobili(toAutomobileDTO(a.getContent()));
        dto.setNumeroPagina(a.getNumber()+1);
        dto.setPagineTotali(a.getTotalPages());
        return dto;
    }

    public PaginaGenericaDTO<Autovettura> toPaginaGenericaDTO(Page<Autovettura> a){
        return pageMapper.toPaginaGenericaDTO(a);
    }

    public Autovettura toAutovettura(ModificaAutomobileDTO dto, Persona p) {
        Autovettura a = new Autovettura();
        a.setId(dto.getId());
        a.setDescrizione(dto.getDescrizione());
        a.setModello(dto.getModello());
        a.setMarca(dto.getMarca());
        a.setAnnoImmatricolazione(dto.getAnnoImmatricolazione());
        a.setProprietario(p);
        a.setVersion(dto.getVersion());
        return a;
    }
}
