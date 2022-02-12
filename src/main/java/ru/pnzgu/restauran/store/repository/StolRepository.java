package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.Reserv;
import ru.pnzgu.restauran.store.entity.Stol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StolRepository extends JpaRepository<Stol, Long> {
    List<Reserv> findReservById(Long id);

    @Query(value = "SELECT MIN(id_table) FROM stol", nativeQuery = true)
    Optional<Long> findMinIdStol();
}