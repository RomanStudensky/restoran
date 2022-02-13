package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.Setter;
import ru.pnzgu.restauran.store.Role;
import ru.pnzgu.restauran.store.Status;

import javax.persistence.*;

@Table(name = "usr")
@Entity
@Getter
@Setter
public class User extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long id;

    @Column(name = "fio", nullable = false, length = 50)
    private String fio;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

}