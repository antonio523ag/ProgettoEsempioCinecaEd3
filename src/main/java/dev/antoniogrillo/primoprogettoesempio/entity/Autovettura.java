package dev.antoniogrillo.primoprogettoesempio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "marca_modello_anno_uni",columnNames = {"anno_di_immatricolazione","marca","modello"})
})
public class Autovettura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String marca;
    //anche senza il @Column verrà comunque inserito nel db
    private String modello;
    @Column(name="anno_di_immatricolazione")
    private int annoImmatricolazione;
    @Lob
    private String descrizione;
    //TRANSIENT serve a dire che quest'attributo non sarà presente sul db
    @Transient
    private boolean nuova;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="persona_fk",nullable = false)
    private Persona proprietario;

    public Autovettura(){}

    public Autovettura(String marca, String modello, int annoImmatricolazione) {
        this.marca = marca;
        this.modello = modello;
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(int annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isNuova() {
        return nuova;
    }

    public void setNuova(boolean nuova) {
        this.nuova = nuova;
    }

    public Persona getProprietario() {
        return proprietario;
    }

    public void setProprietario(Persona proprietario) {
        this.proprietario = proprietario;
    }
}
