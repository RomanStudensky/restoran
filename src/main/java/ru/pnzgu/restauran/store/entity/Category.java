package ru.pnzgu.restauran.store.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "category")
@Entity
@Data
public class Category extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat", nullable = false)
    private Long id;

    @Column(name = "name_cat", nullable = false, length = 20)
    private String nameCat;

}