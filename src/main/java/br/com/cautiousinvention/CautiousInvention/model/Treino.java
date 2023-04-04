package br.com.cautiousinvention.CautiousInvention.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "treinos")
public class Treino implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
