package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "sostav_order")
@Entity
@Getter
@Setter
public class SostavOrder extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sost_order", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orders", nullable = false)
    private Order orders;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}