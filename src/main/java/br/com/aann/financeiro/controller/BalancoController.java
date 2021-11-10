package br.com.aann.financeiro.controller;

import br.com.aann.financeiro.annotation.ApiV1;
import br.com.aann.financeiro.constants.FormatosDatas;
import br.com.aann.financeiro.dto.BalancoDTO;
import br.com.aann.financeiro.filter.BalancoFilter;
import br.com.aann.financeiro.http.Response;
import br.com.aann.financeiro.service.BalancoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@ApiV1("/v1/balanco")
@Api(value = "Balanço dos lançamentos", tags = {"balanco"})
@AllArgsConstructor
public class BalancoController {

    private final BalancoService balancoService;

    @GetMapping
    @ApiOperation("Método para recuperar o saldo total dos lançamentos")
    public Response<BalancoDTO> recuperarBalanco(
            @RequestParam(value = "data_inicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = FormatosDatas.FORMATO_DATA_BR) final LocalDate dataInicio,
            @RequestParam(value = "data_fim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = FormatosDatas.FORMATO_DATA_BR) final LocalDate dataFim,
            @RequestParam(value = "id_categoria", required = false) final Long idCategoria) {
        return new Response<>(balancoService.recuperarBalanco(
                BalancoFilter.builder().withDataInicio(dataInicio).withDataFim(dataFim).withIdCategoria(idCategoria)
                        .build()));
    }
}
