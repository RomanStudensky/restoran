package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.Naklad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NakladRepository extends JpaRepository<Naklad, Long> {
    @Query(value = "SELECT MIN(id_sost_post) FROM sostav_postav", nativeQuery = true)
    Optional<Long> findMinIdNaklad();

    List<Naklad> findAllByPostavshikId(Long postavshik_id);

    void deleteById(Long id);
}