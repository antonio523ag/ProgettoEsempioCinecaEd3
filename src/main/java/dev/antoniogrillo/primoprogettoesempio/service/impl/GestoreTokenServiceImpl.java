package dev.antoniogrillo.primoprogettoesempio.service.impl;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import dev.antoniogrillo.primoprogettoesempio.repository.PersonaRepository;
import dev.antoniogrillo.primoprogettoesempio.service.def.GestoreTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class GestoreTokenServiceImpl implements GestoreTokenService {

    private final PersonaRepository repo;

    @Value("${jwt.secret}")
    private String keyToken;

    @Value("${jwt.expiration}")
    private long durata;

    public GestoreTokenServiceImpl(PersonaRepository repo) {
        this.repo = repo;
    }

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyToken));
    }

    @Override
    public String generaToken(Persona persona) {
        JwtBuilder.BuilderClaims builderClaim= Jwts.builder()
                .claims();
        builderClaim.subject(persona.getEmail());
        builderClaim.issuedAt(new Date(System.currentTimeMillis()));
        builderClaim.expiration(new Date(System.currentTimeMillis() + durata));
        builderClaim.add("miaCustom","testo custom senza senso");
        return builderClaim.and().signWith(getKey()).compact();
    }

    @Override
    public boolean verificaToken(String token) {
        Claims claims=getClaims(token);
        if(claims==null)return false;
        Date expiration=getExpiration(token);
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    @Override
    public Persona getPersonaByToken(String token) {
        if(token==null) return null;
        if(token.startsWith("Bearer "))token=token.substring(7);
        String email=getSubject(token);
        if(email==null)return null;
        return repo.findByEmail(email).orElse(null);
    }

    private Claims getClaims(String token){
        JwtParser parser= Jwts.parser()
                .verifyWith(getKey())
                .build();
        try{
            return (Claims) parser.parse(token).getPayload();
        }catch (JwtException e){
            return null;
        }
    }

    private String getSubject(String token){
        return getClaims(token)==null?null:getClaims(token).getSubject();
    }

    private Date getExpiration(String token){
        return getClaims(token).getExpiration();
    }

    private Object getCustom(String token,String chiave){
        return getClaims(token).get(chiave);
    }

}
