package ru.pnzgu.restauran.store.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Table(name = "reserv")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Reserv extends EntityParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserv", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "date_reserv", nullable = false)
    private LocalDate dateReserv;

    @Column(name = "time_reserv", nullable = false)
    private LocalTime timeReserv;

    @Column(name = "count_people", nullable = false)
    private Integer countPeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_table")
    @ToString.Exclude
    private Stol stol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reserv reserv = (Reserv) o;
        return id != null && Objects.equals(id, reserv.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}