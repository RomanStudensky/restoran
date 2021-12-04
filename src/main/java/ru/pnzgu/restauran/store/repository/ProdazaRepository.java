package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.Prodaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdazaRepository extends JpaRepository<Prodaza, Long> {
    @Query(value = "SELECT MIN(id_zakaz) FROM zakaz", nativeQuery = true)
    Optional<Long> getFirstProdaza();

}