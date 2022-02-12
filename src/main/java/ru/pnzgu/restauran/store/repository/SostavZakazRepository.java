package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.SostavZakaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SostavZakazRepository extends JpaRepository<SostavZakaz, Long> {
    List<SostavZakaz> findAllByZakazId(Long id);
}