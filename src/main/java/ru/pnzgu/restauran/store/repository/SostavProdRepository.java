package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.SostavProd;

import java.util.List;

@Repository
public interface SostavProdRepository extends JpaRepository<SostavProd, Long> {
    List<SostavProd> findByProdazaId(Long id);
}