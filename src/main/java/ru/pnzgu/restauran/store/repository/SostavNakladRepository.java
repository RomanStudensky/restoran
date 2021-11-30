package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.SostavNaklad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SostavNakladRepository extends JpaRepository<SostavNaklad, Long> {
}