package ru.pnzgu.restauran.store.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "akt_spis")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AktSpis extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_akt", nullable = false)
    private Long id;

    @Column(name = "date_akt", nullable = false)
    private LocalDate dateAkt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sotrud", nullable = false)
    private User sotrud;

    @Column(name = "summa", nullable = false)
    private BigDecimal summa;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "akt")
    private List<SostavAkt> spisProducts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AktSpis aktSpi = (AktSpis) o;
        return id != null && Objects.equals(id, aktSpi.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}