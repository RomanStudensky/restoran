package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "orders")
@Entity
@Getter
@Setter
public class Order extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Long id;

    @Column(name = "date_order", nullable = false)
    private LocalDate dateOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postavshik", nullable = false)
    private Postavshik postavshik;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dogovor", nullable = false)
    private Dogovor dogovor;

}