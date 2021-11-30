package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.AktSpis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AktRepository extends JpaRepository<AktSpis, Long> {
}