package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.Query;
import ru.pnzgu.restauran.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT MIN(id_cat) FROM category", nativeQuery = true)
    Optional<Long> findMinIdCategory();
}