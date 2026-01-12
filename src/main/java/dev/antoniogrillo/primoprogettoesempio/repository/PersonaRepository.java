package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
