package br.com.cautiousinvention.CautiousInvention.shared;

import br.com.cautiousinvention.CautiousInvention.model.Exercicio;
import br.com.cautiousinvention.CautiousInvention.model.Usuario;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class TreinoDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String descricao;

    private Usuario usuario;

    private List<Exercicio> exercicios = new ArrayList<>();

}
