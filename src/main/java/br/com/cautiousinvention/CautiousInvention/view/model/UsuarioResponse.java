package br.com.cautiousinvention.CautiousInvention.view.model;

import br.com.cautiousinvention.CautiousInvention.model.Treino;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioResponse implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Integer idade;
    private String sexo;
    private Double peso;
    private Double altura;
    private List<Treino> treinos = new ArrayList<>();

    public UsuarioResponse() {
    }

    public UsuarioResponse(Integer id, String nome, String email, String senha, Integer idade, String sexo, Double peso, Double altura, List<Treino> treinos) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }
}
