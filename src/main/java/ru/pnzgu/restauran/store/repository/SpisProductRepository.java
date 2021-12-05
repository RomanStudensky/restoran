package ru.pnzgu.restauran.store.repository;

import ru.pnzgu.restauran.store.entity.SpisProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpisProductRepository extends JpaRepository<SpisProduct, Long> {

}