package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Autovettura;
import jakarta.persistence.Lob;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutovetturaRepository extends JpaRepository<Autovettura,Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select a from Autovettura a where a.proprietario.id = :idUtente")
    List<Autovettura> getAllByIdUtente(long idUtente);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Page<Autovettura> getAllByProprietario_Id(long idUtente, Pageable p);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Autovettura> findById(long id);
    //quando navigo all'interno delle proprietà delle classi utilizzo _ quindi _id si riferisce all'id del proprietario
    //con la findAll prendo la Collection di entities
    List<Autovettura> findAllByProprietario_IdAndModelloIsNotNull(long idUtente);

}
