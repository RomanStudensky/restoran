package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "dogovor")
@Entity
@Getter
@Setter
public class Dogovor extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dogovor", nullable = false)
    private Long id;

    @Column(name = "date_dogovor", nullable = false)
    private LocalDate dateDogovor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postavshik", nullable = false)
    private Postavshik postavshik;

    @Column(name = "sostav", nullable = false)
    private String sostav;
}