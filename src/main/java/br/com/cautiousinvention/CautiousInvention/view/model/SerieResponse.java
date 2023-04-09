package br.com.cautiousinvention.CautiousInvention.view.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SerieResponse implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    private Integer repeticoes;

    private Double carga;
}
