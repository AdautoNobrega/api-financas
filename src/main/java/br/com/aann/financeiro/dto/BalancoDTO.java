package br.com.aann.financeiro.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BalancoDTO implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -2573864174734033629L;

    private CategoriaDTO categoria;

    private BigDecimal receita;

    private BigDecimal despesa;

    private BigDecimal saldo;
}
