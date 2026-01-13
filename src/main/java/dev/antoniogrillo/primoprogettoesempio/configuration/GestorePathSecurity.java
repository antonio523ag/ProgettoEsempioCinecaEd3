package dev.antoniogrillo.primoprogettoesempio.configuration;

import dev.antoniogrillo.primoprogettoesempio.entity.Ruolo;
import dev.antoniogrillo.primoprogettoesempio.filter.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class GestorePathSecurity {

    private final JWTFilter filter;
    private final AuthenticationProvider provider;

    public GestorePathSecurity(JWTFilter filter, AuthenticationProvider provider) {
        this.filter = filter;
        this.provider = provider;
    }

    @Bean
    protected SecurityFilterChain getChain(HttpSecurity http){
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers(cust -> cust.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(r->r
                        .requestMatchers("/api/persona/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/persona/id/*").hasRole(Ruolo.ADMIN.name())
                        .requestMatchers("/indirizzo/add").hasAnyRole(Ruolo.ADMIN.name(),Ruolo.UTENTE.name())
                        .requestMatchers("/api/persona/salva").permitAll()
                        .requestMatchers("/console/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
