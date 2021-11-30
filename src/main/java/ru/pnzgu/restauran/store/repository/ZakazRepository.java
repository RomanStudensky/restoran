package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.Zakaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZakazRepository extends JpaRepository<Zakaz, Long> {
}