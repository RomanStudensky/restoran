package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.Postavshik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostavshikRepository extends JpaRepository<Postavshik, Long> {
}