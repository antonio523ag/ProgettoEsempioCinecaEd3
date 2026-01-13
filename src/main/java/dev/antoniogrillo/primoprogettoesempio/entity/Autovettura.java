package dev.antoniogrillo.primoprogettoesempio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "marca_modello_anno_uni",columnNames = {"anno_di_immatricolazione","marca","modello"})
})
@Getter //crea tutti i getter (se usato sulle singole variabili crea il getter della singola variabile)
@Setter //crea tutti i setter (se usato sulle singole variabili crea il setter della singola variabile)
@NoArgsConstructor //crea un costruttore vuoto
@AllArgsConstructor //crea un costruttore con tutti i parametri, i parametri saranno nell'ordine di definizione
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

    }
