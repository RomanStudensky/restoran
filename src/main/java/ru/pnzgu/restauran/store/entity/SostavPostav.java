package ru.pnzgu.restauran.store.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "sostav_postav")
@Entity
@Data
public class SostavPostav extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sost_post", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tovar_naklad", nullable = false)
    private Naklad tovarNaklad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false, precision = 131089)
    private BigDecimal price;

    @Column(name = "summa", nullable = false, precision = 131089)
    private Double summa;

}