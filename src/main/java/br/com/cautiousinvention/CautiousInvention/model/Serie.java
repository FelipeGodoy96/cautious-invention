package br.com.cautiousinvention.CautiousInvention.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "series")
public class Serie implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer repeticoes;

    private Double carga;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Serie serie = (Serie) o;
        return getId() != null && Objects.equals(getId(), serie.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
