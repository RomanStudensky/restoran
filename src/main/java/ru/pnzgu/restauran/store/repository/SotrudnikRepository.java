package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.Sotrudnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SotrudnikRepository extends JpaRepository<Sotrudnik, Long> {
}