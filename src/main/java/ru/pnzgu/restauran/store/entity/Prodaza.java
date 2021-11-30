package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "prodaza")
@Entity
@Setter
@Getter
public class Prodaza extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod", nullable = false)
    private Long id;

    @Column(name = "date_prod", nullable = false)
    private LocalDate dateProd;

    @Column(name = "time_prod", nullable = false)
    private LocalTime timeProd;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sotrud", nullable = false)
    private Sotrudnik sotrud;

    @Column(name = "summa", nullable = false, precision = 131089)
    private BigDecimal summa;

}