package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.Prodaza;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdazaRepository extends JpaRepository<Prodaza, Long> {
    @Query(value = "SELECT MIN(id_zakaz) FROM zakaz", nativeQuery = true)
    Optional<Long> getFirstProdaza();

    List<Prodaza> findAllByDateProd(LocalDate dateProd);

}