package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.Reserv;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservRepository extends JpaRepository<Reserv, Long> {
    List<Reserv> findAllReservByStolId(Long id);

    @Query(value = "SELECT id_table FROM reserv WHERE id_reserv = :id", nativeQuery = true)
    Optional<Long> findStolById(@Param(value = "id") Long id);
}