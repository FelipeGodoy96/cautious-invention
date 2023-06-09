package br.com.cautiousinvention.CautiousInvention.shared;

import br.com.cautiousinvention.CautiousInvention.model.Treino;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UsuarioDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    private String nome;

    private String email;

    private String senha;

    private Integer idade;

    private String sexo;

    private Double peso;

    private Double altura;

    private Set<Treino> treinos;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id, String nome, String email, String senha, Integer idade, String sexo, Double peso, Double altura, Set<Treino> treinos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.treinos = treinos;
    }
}
