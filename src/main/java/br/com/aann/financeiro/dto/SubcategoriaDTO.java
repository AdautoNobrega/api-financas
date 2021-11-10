package br.com.aann.financeiro.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class SubcategoriaDTO implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -5956721219022666529L;

    @JsonProperty("id_subcategoria")
    private Long id;

    private String nome;

    @JsonProperty("id_categoria")
    private Long idCategoria;
}
