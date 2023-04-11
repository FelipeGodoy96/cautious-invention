package br.com.cautiousinvention.CautiousInvention.view.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UsuarioRequest implements Serializable {
    private static final Long serialVersionUID = 1L;
    private String nome;
    private String email;
    private String senha;
    private Integer idade;
    private String sexo;
    private Double peso;
    private Double altura;

    // Posso passar um array vazio na criação de um usuário.
    private Set<Integer> treinos_id = new HashSet<>();

    public UsuarioRequest(String nome, String email, String senha, Integer idade, String sexo, Double peso, Double altura, Set<Integer> treinos_id) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.treinos_id = treinos_id;
    }
}
