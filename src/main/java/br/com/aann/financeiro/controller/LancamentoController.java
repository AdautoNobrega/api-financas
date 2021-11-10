package br.com.aann.financeiro.controller;

import br.com.aann.financeiro.annotation.ApiV1;
import br.com.aann.financeiro.dto.LancamentoDTO;
import br.com.aann.financeiro.http.Response;
import br.com.aann.financeiro.service.LancamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@ApiV1("/v1/lancamentos")
@Api(value = "CRUD dos lançamentos", tags = {"lancamento"})
@AllArgsConstructor
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @GetMapping
    @ApiOperation(value = "Buscar todos os lançamentos")
    public Response<List<LancamentoDTO>> consultarLancamentos() {
        return new Response<>(lancamentoService.recuperarLancamentos());
    }

    @GetMapping("/{id_lancamento}")
    @ApiOperation(value = "Buscar lançamentos por id")
    public Response<LancamentoDTO> consultarLancamentos(@PathVariable("id_lancamento") final Long idLancamento) {
        return new Response<>(lancamentoService.recuperarLancamentoPorId(idLancamento));
    }

    @PostMapping
    @ApiOperation(value = "Criar lançamento de uma subcategoria")
    public Response<LancamentoDTO> criarLancamento(@RequestBody final LancamentoDTO lancamentoDTO) {
        return new Response<>(lancamentoService.criarLancamento(lancamentoDTO));
    }

    @PutMapping
    @ApiOperation(value = "Editar um lançamento")
    public Response<LancamentoDTO> editarLancamento(@RequestBody final LancamentoDTO lancamentoDTO) {
        return new Response<>(lancamentoService.editarLancamento(lancamentoDTO));
    }

    @DeleteMapping("{id_lancamento}")
    @ApiOperation(value = "Excluir lançamentos por id")
    public Response<Boolean> excluirLancamento(@PathVariable("id_lancamento") final Long idLancamento) {
        return new Response<>(lancamentoService.excluirLancamento(idLancamento));
    }
}