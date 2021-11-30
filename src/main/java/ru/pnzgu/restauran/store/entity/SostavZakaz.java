package ru.pnzgu.restauran.store.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "sostav_zakaz")
@Entity
@Data
public class SostavZakaz extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sost_zak", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "zakaz", nullable = false)
    private Zakaz zakaz;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bludo", nullable = false)
    private Menu bludo;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "summa", nullable = false, precision = 131089)
    private BigDecimal summa;

}