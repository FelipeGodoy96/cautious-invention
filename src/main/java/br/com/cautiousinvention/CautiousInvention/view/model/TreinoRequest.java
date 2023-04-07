package br.com.cautiousinvention.CautiousInvention.view.model;

import br.com.cautiousinvention.CautiousInvention.model.Exercicio;
import br.com.cautiousinvention.CautiousInvention.model.Usuario;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class TreinoRequest implements Serializable {

    private final static Long serialVersionUID = 1L;

    private String nome;

    private String descricao;

    private Usuario usuario;

    private Set<Exercicio> exercicios = new HashSet<>();


}
