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
public class CategoriaDTO implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 3308008699944789749L;

    @JsonProperty("id_categoria")
    private Long id;

    private String nome;

    public CategoriaDTO(String nome) {
        this.nome = nome;
    }
}
