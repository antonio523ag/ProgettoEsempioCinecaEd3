package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    @Query(nativeQuery = true, value = "select * from Persona where email = :username and password = :password")
    Optional<Persona> loginSQLStandard(String username,String password);

    @Query("select p from Persona p where p.email = :username and p.password = :password")
    Optional<Persona> loginJPQL(String username,String password);

    //con il findBy prendo il singolo oggetto e successivamente inserisco i parametri
    Optional<Persona> findByEmailAndPassword(String username,String password);

    Optional<Persona> findByEmail(String email);

    //questa non funziona perché in Persona non esiste "Portafoglio"
    //Optional<Persona> findByPortafoglioIsNotNull();

}
