package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Autovettura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutovetturaRepository extends JpaRepository<Autovettura,Long> {
}
