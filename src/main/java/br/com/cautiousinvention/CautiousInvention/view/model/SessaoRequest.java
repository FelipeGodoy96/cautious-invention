package br.com.cautiousinvention.CautiousInvention.view.model;

import br.com.cautiousinvention.CautiousInvention.model.Treino;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SessaoRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Date data;

    private Treino treino;
}
