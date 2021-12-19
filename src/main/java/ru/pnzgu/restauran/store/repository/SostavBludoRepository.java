package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.SostavBludo;

import java.util.Optional;

@Repository
public interface SostavBludoRepository extends JpaRepository<SostavBludo, Long> {

    Optional<SostavBludo> findAllByMenu_Id(Long menu_id);
}