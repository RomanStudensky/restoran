package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "sostav_prod")
@Entity
@Getter
@Setter
public class SostavProd extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sost_prod", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prodaza", nullable = false)
    private Prodaza prodaza;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bludo", nullable = false)
    private Menu bludo;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "summa", nullable = false, precision = 131089)
    private Double summa;

}