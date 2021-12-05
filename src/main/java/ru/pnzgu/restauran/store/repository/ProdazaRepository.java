package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.Prodaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdazaRepository extends JpaRepository<Prodaza, Long> {
}