package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Autovettura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutovetturaRepository extends JpaRepository<Autovettura,Long> {
    @Query("select a from Autovettura a where a.proprietario.id = :idUtente")
    List<Autovettura> getAllByIdUtente(long idUtente);

    //quando navigo all'interno delle proprietà delle classi utilizzo _ quindi _id si riferisce all'id del proprietario
    //con la findAll prendo la Collection di entities
    List<Autovettura> findAllByProprietario_IdAndModelloIsNotNull(long idUtente);

}
