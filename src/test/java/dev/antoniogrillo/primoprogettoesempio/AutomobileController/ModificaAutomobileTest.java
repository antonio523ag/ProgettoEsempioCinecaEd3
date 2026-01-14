package dev.antoniogrillo.primoprogettoesempio.AutomobileController;

import dev.antoniogrillo.primoprogettoesempio.PrimoProgettoEsempioApplication;
import dev.antoniogrillo.primoprogettoesempio.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.dto.request.ModificaAutomobileDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;


@SpringBootTest
@ContextConfiguration(classes = PrimoProgettoEsempioApplication.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class ModificaAutomobileTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @Order(1)
    public void modificaAutomobileGiusta() throws Exception{
        ModificaAutomobileDTO m=new ModificaAutomobileDTO();
        m.setId(2);
        m.setDescrizione("nuova descrizione");
        m.setModello("500x");
        m.setMarca("Fiat");
        m.setAnnoImmatricolazione(2021);
        m.setIdProprietario(1);
        m.setVersion(1);
        String token=getToken();
        RequestBuilder request= MockMvcRequestBuilders.put("/api/automobili")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","Bearer "+ token)
                .content(mapper.writeValueAsString(m));
        ResultMatcher r1= MockMvcResultMatchers.status().isOk();
        ResultMatcher r2= MockMvcResultMatchers.jsonPath("$.descrizione").isString();
        ResultMatcher r3= MockMvcResultMatchers.jsonPath("$.modello").value("500x");
        ResultMatcher r4= MockMvcResultMatchers.jsonPath("$.marca").value("Fiat");
        ResultMatcher r5= MockMvcResultMatchers.jsonPath("$.annoImmatricolazione").value(2021);
        ResultMatcher r6=MockMvcResultMatchers.jsonPath("$.version").value(1);
        mock.perform(request)
                .andExpect(r1)
                .andExpect(r2)
                .andExpect(r3)
                .andExpect(r4)
                .andExpect(r5)
                .andExpect(r6)
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    @Order(3)
    public void modificaAutomobileNonEsistente() throws Exception{
        ModificaAutomobileDTO m=new ModificaAutomobileDTO();
        m.setId(1000);
        RequestBuilder request= MockMvcRequestBuilders.put("/api/automobili")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(m))
                .header("Authorization","Bearer "+ getToken());
        mock.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    public void modificaAutomobileNonAutenticato() throws Exception {
        ModificaAutomobileDTO m=new ModificaAutomobileDTO();
        m.setId(2);
        m.setDescrizione("nuova descrizione");
        m.setModello("500x");
        m.setMarca("Fiat");
        m.setAnnoImmatricolazione(2021);
        m.setIdProprietario(1);
        m.setVersion(1);
        mock.perform(MockMvcRequestBuilders.put("/api/automobili")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(m)))
                .andExpect(MockMvcResultMatchers.status().is(403))
                .andReturn();

    }

    @Test
    @Order(3)
    public void modificaAutomobileConVersionErrata() throws Exception {
        ModificaAutomobileDTO m=new ModificaAutomobileDTO();
        m.setId(5);
        m.setDescrizione("nuova descrizione");
        m.setModello("500x");
        m.setMarca("Fiat");
        m.setAnnoImmatricolazione(2021);
        m.setIdProprietario(1);
        m.setVersion(100);
        String token=getToken();
        RequestBuilder request= MockMvcRequestBuilders.put("/api/automobili")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization","Bearer "+ token)
                .content(mapper.writeValueAsString(m));
        ResultMatcher r1= MockMvcResultMatchers.status().isConflict();
         mock.perform(request)
                .andExpect(r1)
                .andDo(MockMvcResultHandlers.print());
    }

    private String getToken() throws Exception {
        LoginRequestDTO l=new LoginRequestDTO();
        l.setUsername("m.rossi@gmail.com");
        l.setPassword("123456");
        String json=mapper.writeValueAsString(l);
        RequestBuilder request= MockMvcRequestBuilders.post("/api/persona/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        return mock.perform(request).andReturn().getResponse().getHeader("Authorization");
    }



}
