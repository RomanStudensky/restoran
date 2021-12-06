package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.Dogovor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.Postavshik;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogovorRepository extends JpaRepository<Dogovor, Long> {
    List<Dogovor> findDogovorsByPostavshikId(Long id);
}