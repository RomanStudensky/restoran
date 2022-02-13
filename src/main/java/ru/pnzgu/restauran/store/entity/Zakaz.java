package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "zakaz")
@Entity
@Getter
@Setter
public class Zakaz extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zakaz", nullable = false)
    private Long id;

    @Column(name = "date_zakaz", nullable = false)
    private LocalDate dateZakaz;

    @Column(name = "time_zakaz", nullable = false)
    private LocalTime timeZakaz;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "stol", nullable = false)
    private Stol stol = new Stol();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "sotrud", nullable = false)
    private User sotrud = new User();

    @Column(name = "summa", nullable = false, precision = 131089)
    private BigDecimal summa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zakaz", fetch = FetchType.LAZY)
    private List<SostavZakaz> sostav = new ArrayList<>();

}