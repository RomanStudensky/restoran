package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "category")
@Entity
@Getter
@Setter
public class Category extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat", nullable = false)
    private Long id;

    @Column(name = "name_cat", nullable = false, length = 20)
    private String nameCat;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
    private List<Menu> menuList = new ArrayList<>();

}