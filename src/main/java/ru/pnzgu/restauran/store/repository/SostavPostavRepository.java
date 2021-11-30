package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.SostavPostav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SostavPostavRepository extends JpaRepository<SostavPostav, Long> {
    List<SostavPostav> findAllByTovarNakladId(Long id);
}