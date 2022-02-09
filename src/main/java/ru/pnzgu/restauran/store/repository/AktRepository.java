package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.AktSpis;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AktRepository extends JpaRepository<AktSpis, Long> {
    @Query(value = "SELECT MIN(id_akt) FROM akt_spis", nativeQuery = true)
    Optional<Long> findMinIdAkt();

    List<AktSpis> findByDateAktBetween(LocalDate dateAkt, LocalDate dateAkt2);
}