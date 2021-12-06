package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.pnzgu.restauran.store.Role;

import javax.persistence.*;

@Table(name = "usr")
@Entity
@Getter
@Setter
public class Sotrudnik extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sotrud", nullable = false)
    private Long id;

    @Column(name = "fio", nullable = false, length = 50)
    private String fio;

    @Column(name = "post", nullable = false, length = 15)
    private String post;


}