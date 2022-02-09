package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "spis_product")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SostavAkt extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spis", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "akt")
    private AktSpis aktSpis;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @Column(name = "reason", nullable = false, length = 100)
    private String reason;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "price", nullable = false, precision = 131089)
    private Double price;

    @Column(name = "summa", nullable = false, precision = 131089)
    private Double summa;
}