package br.com.cautiousinvention.CautiousInvention.view.model;

import br.com.cautiousinvention.CautiousInvention.model.Exercicio;
import br.com.cautiousinvention.CautiousInvention.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TreinoResponse implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Usuario usuario;

    @OneToMany(mappedBy = "treino")
    private Set<Exercicio> exercicios = new HashSet<>();

    public TreinoResponse() {
    }

    public TreinoResponse(Integer id, String nome, String descricao, Usuario usuario, Set<Exercicio> exercicios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.usuario = usuario;
        this.exercicios = exercicios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(Set<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
}
