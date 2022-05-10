package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "stol")
@Entity
@Getter
@Setter
public class Stol extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_table", nullable = false)
    private Long id;

    @Column(name = "count_place", nullable = false)
    private Integer countPlace;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stol", fetch = FetchType.LAZY)
    private List<Reserv> reservs;
}