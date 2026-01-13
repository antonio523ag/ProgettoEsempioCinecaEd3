package dev.antoniogrillo.primoprogettoesempio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Persona implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String cognome;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate dataNascita;
    private Ruolo ruolo;



    //select * from autovettura where persona_fk = ?id
    @OneToMany(mappedBy = "proprietario",cascade = CascadeType.MERGE)
    private List<Autovettura> autoPossedute;

    @ManyToMany
    @JoinTable(name="indirizzi_della_persona",
            joinColumns = @JoinColumn(name = "persona_fk",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "indirizzo_fk",nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"indirizzo_fk","persona_fk"},name = "indirizzi_univoci"))
    private List<Indirizzo> indirizzi;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
