package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.SostavAkt;

import java.util.List;

@Repository
public interface SostavAktRepository extends JpaRepository<SostavAkt, Long> {
    List<SostavAkt> findSostavAktByAktSpis_Id(Long aktSpis_id);
}