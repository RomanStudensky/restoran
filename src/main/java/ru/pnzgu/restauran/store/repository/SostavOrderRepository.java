package ru.pnzgu.restauran.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.restauran.store.entity.SostavOrder;

import java.util.List;

@Repository
public interface SostavOrderRepository extends JpaRepository<SostavOrder, Long> {
    List<SostavOrder> findAllByOrdersId(Long id);
}