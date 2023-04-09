package br.com.cautiousinvention.CautiousInvention.shared;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class SerieDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    private Integer repeticoes;

    private Double carga;
}
