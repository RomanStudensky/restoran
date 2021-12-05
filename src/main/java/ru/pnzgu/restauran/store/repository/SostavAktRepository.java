package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.SostavAkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SostavAktRepository extends JpaRepository<SostavAkt, Long> {
    List<SostavAkt> findSostavAktByAktSpis_Id(Long aktSpis_id);
}