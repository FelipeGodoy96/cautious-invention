package br.com.cautiousinvention.CautiousInvention.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "exercicios")
public class Exercicio implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String grupo;

//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId=true)
//    @JoinColumn(name = "exercicio_id")
//    @JsonIgnore
//    @ManyToOne
//    private Treino treino;

    // remover -- possível many to many
    @ManyToMany
    @JoinTable(name = "exercicios_series",
        joinColumns = @JoinColumn(name = "exercicio_id"),
        inverseJoinColumns = @JoinColumn(name = "serie_id")
    )
    private Set<Serie> series = new HashSet<>();

}
