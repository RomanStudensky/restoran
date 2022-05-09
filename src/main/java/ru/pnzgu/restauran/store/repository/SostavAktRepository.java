package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.AktSpis;
import ru.pnzgu.restauran.store.entity.SostavAkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SostavAktRepository extends JpaRepository<SostavAkt, Long> {
    List<SostavAkt> findAllByAktSpis_Id(Long aktSpis_id);

    @Query(value = "select s.aktSpis.id from SostavAkt s where s.id = ?1")
    Long getAktSpisIdBySostavAktId(Long sostavAktId);
}