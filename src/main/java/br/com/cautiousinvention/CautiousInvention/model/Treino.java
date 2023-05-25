package br.com.cautiousinvention.CautiousInvention.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "treinos")
@NoArgsConstructor
@AllArgsConstructor
public class Treino implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;


//    @JsonDeserialize
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId=true)

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuario;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "treino_id")
    private Set<Exercicio> exercicios;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Treino treino = (Treino) o;
        return getId() != null && Objects.equals(getId(), treino.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
