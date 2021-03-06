package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.Postavshik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostavshikRepository extends JpaRepository<Postavshik, Long> {
    @Query(value = "SELECT MIN(id_post) FROM postavshik", nativeQuery = true)
    Optional<Long> findMinIdPostavshik();
}