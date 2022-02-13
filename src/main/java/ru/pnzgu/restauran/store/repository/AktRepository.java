package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.AktSpis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AktRepository extends JpaRepository<AktSpis, Long> {
    @Query(value = "SELECT MIN(id_akt) FROM akt_spis", nativeQuery = true)
    Optional<Long> findMinIdAkt();

    List<AktSpis> findByDateAktBetween(LocalDate dateAkt, LocalDate dateAkt2);

    @Query(value = "" +
            "select *  " +
            "from akt_spis " +
            "join usr u on u.id_user = akt_spis.usr and u.username = ?1",
            nativeQuery = true)
    List<AktSpis> findAllByUsername(String username);
}