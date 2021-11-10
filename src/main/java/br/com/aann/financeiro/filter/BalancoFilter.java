package br.com.aann.financeiro.filter;

import br.com.aann.financeiro.constants.FormatosDatas;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class BalancoFilter implements Serializable {

    private static final long serialVersionUID = 7970397162238197541L;

    @JsonProperty("data_inicio")
    @JsonFormat(pattern = FormatosDatas.FORMATO_DATA_BR)
    private LocalDate dataInicio;

    @JsonProperty("data_fim")
    @JsonFormat(pattern = FormatosDatas.FORMATO_DATA_BR)
    private LocalDate dataFim;

    @JsonProperty("id_categoria")
    private Long idCategoria;
}
