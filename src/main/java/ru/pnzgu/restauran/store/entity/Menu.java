package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "menu")
@Entity
@Getter
@Setter
public class Menu extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu", nullable = false)
    private Long id;

    @Column(name = "bludo", nullable = false, length = 30)
    private String bludo;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "price", nullable = false, precision = 131089)
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.LAZY)
    private List<SostavBludo> sostavList = new ArrayList<>();
}