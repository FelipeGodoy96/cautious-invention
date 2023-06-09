package br.com.cautiousinvention.CautiousInvention.shared;

import br.com.cautiousinvention.CautiousInvention.model.Treino;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SessaoDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    private Date data;

    private Treino treino;

}
