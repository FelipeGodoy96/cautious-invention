package br.com.cautiousinvention.CautiousInvention.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "exercicios")
public class Exercicio implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String grupo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Exercicio exercicio = (Exercicio) o;
        return getId() != null && Objects.equals(getId(), exercicio.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
