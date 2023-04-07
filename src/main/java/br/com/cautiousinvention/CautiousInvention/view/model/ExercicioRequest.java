package br.com.cautiousinvention.CautiousInvention.view.model;

import br.com.cautiousinvention.CautiousInvention.model.Serie;
import br.com.cautiousinvention.CautiousInvention.model.Treino;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ExercicioRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private String grupo_muscular;
    private Treino treino;
    private Set<Serie> series = new HashSet<>();

}
