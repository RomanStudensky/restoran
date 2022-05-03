package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.SostavProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SostavProdRepository extends JpaRepository<SostavProd, Long> {
    List<SostavProd> findByProdazaId(Long id);

    @Query(value = "select s.prodaza.id from SostavProd s where s.id = ?1")
    Long getProdIdBySostavId(Long id);
}