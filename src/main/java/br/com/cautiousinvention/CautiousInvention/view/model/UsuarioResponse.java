package br.com.cautiousinvention.CautiousInvention.view.model;

import br.com.cautiousinvention.CautiousInvention.model.Treino;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
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
    private Set<Treino> treinos = new HashSet<>();

}
