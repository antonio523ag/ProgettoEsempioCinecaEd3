package dev.antoniogrillo.primoprogettoesempio.filter;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.service.def.GestoreTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final GestoreTokenService service;

    public JWTFilter(GestoreTokenService service) {
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token==null||!token.startsWith("Bearer ")||
                SecurityContextHolder.getContext().getAuthentication()!=null){
            filterChain.doFilter(request, response);
            return;
        }
        Persona p=service.getPersonaByToken(token);
        if(p==null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(p,null,p.getAuthorities());
        upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(upat);
        filterChain.doFilter(request, response);
    }
}
