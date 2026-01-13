package dev.antoniogrillo.primoprogettoesempio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
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

    public Persona(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public List<Autovettura> getAutoPossedute() {
        return autoPossedute;
    }

    public void setAutoPossedute(List<Autovettura> autoPossedute) {
        this.autoPossedute = autoPossedute;
    }

    public List<Indirizzo> getIndirizzi() {
        return indirizzi;
    }

    public void setIndirizzi(List<Indirizzo> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }
}
