package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tovar_naklad")
@Entity
@Setter
@Getter
public class Naklad extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_naklad", nullable = false)
    private Long id;

    @Column(name = "date_nak", nullable = false)
    private LocalDate dateNaklad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postavshik", nullable = false)
    private Postavshik postavshik;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dogovor", nullable = false)
    private Dogovor dogovor;

    @Column(name = "summa", nullable = false, precision = 131089)
    private Double summa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tovarNaklad")
    private List<SostavPostav> sostav = new ArrayList<>();

}