package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.SostavProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SostavProdRepository extends JpaRepository<SostavProd, Long> {
    List<SostavProd> findByProdazaId(Long id);
}