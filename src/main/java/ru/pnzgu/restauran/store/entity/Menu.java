package ru.pnzgu.restauran.store.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "menu")
@Entity
@Data
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

}