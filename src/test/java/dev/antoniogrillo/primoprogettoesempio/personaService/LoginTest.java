package dev.antoniogrillo.primoprogettoesempio.personaService;

import dev.antoniogrillo.primoprogettoesempio.PrimoProgettoEsempioApplication;
import dev.antoniogrillo.primoprogettoesempio.dto.request.LoginRequestDTO;
import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.service.def.PersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = PrimoProgettoEsempioApplication.class)
public class LoginTest {

    @Autowired
    private PersonaService service;

    @Test
    public void testLoginEsatto(){
        LoginRequestDTO request=new LoginRequestDTO();
        request.setUsername("m.rossi@gmail.com");
        request.setPassword("123456");
        Persona p=service.login(request);
        assertThat(p).isNotNull();
        assertEquals("Mario", p.getNome());
        assertEquals("Rossi", p.getCognome());
    }
}
