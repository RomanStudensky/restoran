package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.Dogovor;

import java.util.List;

@Repository
public interface DogovorRepository extends JpaRepository<Dogovor, Long> {
    List<Dogovor> findDogovorsByPostavshikId(Long id);
}