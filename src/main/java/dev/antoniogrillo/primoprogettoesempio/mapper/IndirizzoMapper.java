package dev.antoniogrillo.primoprogettoesempio.mapper;

import dev.antoniogrillo.primoprogettoesempio.dto.request.AggiungiIndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.response.IndirizzoDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Indirizzo;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IndirizzoMapper {

    private final PageMapper pageMapper;

    public IndirizzoDTO toIndirizzoDTO(Indirizzo i){
        IndirizzoDTO indirizzoDTO = new IndirizzoDTO();
        indirizzoDTO.setCitta(i.getCitta());
        indirizzoDTO.setCap(i.getCap());
        indirizzoDTO.setCivico(i.getCivico());
        indirizzoDTO.setVia(i.getVia());
        indirizzoDTO.setId(i.getId());
        if(i.getPersone()!=null&&!i.getPersone().isEmpty()){
            List<String> nomiAbitanti=i.getPersone().stream()
                    .map(p->p.getNome()+" "+p.getCognome()).toList();
            indirizzoDTO.setNomeAbitanti(nomiAbitanti);
        }
        return indirizzoDTO;
    }

    public Indirizzo toIndirizzo(AggiungiIndirizzoDTO dto) {
        Indirizzo i=new Indirizzo();
        i.setCitta(dto.getCitta());
        i.setCap(dto.getCap());
        i.setCivico(dto.getCivico());
        i.setVia(dto.getVia());
        return i;
    }
}
