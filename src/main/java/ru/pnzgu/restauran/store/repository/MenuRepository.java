package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.Category;
import ru.pnzgu.restauran.store.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByCategory_Id(Long id_cat);

}
