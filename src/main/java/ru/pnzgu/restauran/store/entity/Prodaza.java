package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "usr", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodaza", fetch = FetchType.EAGER)
    private List<SostavProd> sostavProd = new ArrayList<>();

    @Column(name = "summa", nullable = false, precision = 131089)
    private BigDecimal summa;

}