package ru.pnzgu.restauran.store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.pnzgu.restauran.store.Role;

import javax.persistence.*;

@Table(name = "sotrudnik")
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

    @Column(name = "rol", nullable = false, length = 15)
    private String role;

    @Column(name = "user_name", nullable = false, length = 15)
    private String userName;

    @Column(name = "password", nullable = false, length = 15)
    private String password;
}