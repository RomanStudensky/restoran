package ru.pnzgu.restauran.store.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "postavshik")
@Entity
@Data
public class Postavshik extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Long id;

    @Column(name = "name_post", nullable = false, length = 50)
    private String namePost;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "phone", length = 11)
    private String phone;

}