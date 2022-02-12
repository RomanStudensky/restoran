package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.Zakaz;

import java.util.Optional;

@Repository
public interface ZakazRepository extends JpaRepository<Zakaz, Long> {
    @Query(value = "SELECT MIN(id_zakaz) FROM zakaz", nativeQuery = true)
    Optional<Long> findMinIdZakaz();
}