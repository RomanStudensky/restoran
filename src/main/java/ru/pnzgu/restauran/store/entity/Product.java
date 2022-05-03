package ru.pnzgu.restauran.store.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "product")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod", nullable = false)
    private Long id;

    @Column(name = "name_prod", nullable = false, length = 20)
    private String nameProd;

    @Column(name = "quantity")
    private Double quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}