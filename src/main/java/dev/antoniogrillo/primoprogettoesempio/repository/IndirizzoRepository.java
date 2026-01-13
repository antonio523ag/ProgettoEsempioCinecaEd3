package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndirizzoRepository extends JpaRepository<Indirizzo,Long> {
    Indirizzo findByViaAndCivicoAndCapAndCitta(String via, String civico, String cap, String citta);
}
