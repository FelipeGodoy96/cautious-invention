package br.com.cautiousinvention.CautiousInvention.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sessoes")
public class Sessao implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date data;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sessao_id")
    private Usuario usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sessao sessao = (Sessao) o;
        return getId() != null && Objects.equals(getId(), sessao.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
