package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.pnzgu.restauran.store.entity.Reserv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.Stol;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservRepository extends JpaRepository<Reserv, Long> {
    List<Reserv> findAllReservByStolId(Long id);

    @Query(nativeQuery = true, value = "select r.stol from reserv r where r.id_reserv = ?1")
    Long findStolByReservId(Long id);


}