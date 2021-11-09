package br.com.aann.financeiro.dto;

import br.com.aann.financeiro.constants.FormatosDatas;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDTO implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 6708216575497044199L;

    @JsonProperty("id_lancamento")
    private Long id;

    private BigDecimal valor;

    @JsonFormat(pattern = FormatosDatas.FORMATO_DATA_BR)
    private LocalDate data = LocalDate.now();

    @JsonProperty("id_subcategoria")
    private Long idSubcategoria;

    private String comentario;
}
