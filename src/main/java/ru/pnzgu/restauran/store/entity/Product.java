package ru.pnzgu.restauran.store.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "product")
@Entity
@Data
public class Product extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod", nullable = false)
    private Long id;

    @Column(name = "name_prod", nullable = false, length = 20)
    private String nameProd;

    @Column(name = "quantity")
    private Double quantity;
}