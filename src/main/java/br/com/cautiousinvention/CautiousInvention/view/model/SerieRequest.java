package br.com.cautiousinvention.CautiousInvention.view.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SerieRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer repeticoes;

    private Double carga;
}
