package ru.pnzgu.restauran.store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "sostav_bludo")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SostavBludo extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sostav", nullable = false)
    private Long id;

    @Column(name = "quanity", nullable = false)
    private Integer count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bludo")
    private Menu menu;
}