package ru.pnzgu.restauran.store.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "sotrudnik")
@Entity
@Data
public class Sotrudnik extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sotrud", nullable = false)
    private Long id;

    @Column(name = "fio", nullable = false, length = 50)
    private String fio;

    @Column(name = "rol", nullable = false, length = 15)
    private String role;

}