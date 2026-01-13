package dev.antoniogrillo.primoprogettoesempio.configuration;

import dev.antoniogrillo.primoprogettoesempio.repository.PersonaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBean {

    private final PersonaRepository repo;

    public SecurityBean(PersonaRepository repo) {
        this.repo = repo;
    }

    @Bean
    protected UserDetailsService userDetailsService(){
        return u->repo.findByEmail(u).orElse(null);
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager getAuthenticationManager(AuthenticationConfiguration conf){
        return conf.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationProvider getProvider(){
        DaoAuthenticationProvider dap=new DaoAuthenticationProvider(userDetailsService());
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }
}
