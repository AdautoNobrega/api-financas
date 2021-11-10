package br.com.aann.financeiro.service.impl;

import br.com.aann.financeiro.dto.BalancoDTO;
import br.com.aann.financeiro.dto.LancamentoDTO;
import br.com.aann.financeiro.filter.BalancoFilter;
import br.com.aann.financeiro.service.BalancoService;
import br.com.aann.financeiro.service.CategoriaService;
import br.com.aann.financeiro.service.LancamentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@AllArgsConstructor
public class BalancoServiceImpl implements BalancoService {

    private final LancamentoService lancamentoService;
    private final CategoriaService categoriaService;

    @Override
    public BalancoDTO recuperarBalanco(BalancoFilter filtro) {
        final List<LancamentoDTO> lancamentoDTOS = lancamentoService.recuperarLancamentosPorFiltros(filtro);
        final AtomicReference<BigDecimal> receita = new AtomicReference<>(BigDecimal.ZERO);
        final AtomicReference<BigDecimal> despesa = new AtomicReference<>(BigDecimal.ZERO);
        this.somarValoresReceitaDespesa(lancamentoDTOS, receita, despesa);
        final BigDecimal receita1 = receita.get();
        final BigDecimal despesa1 = despesa.get();
        return BalancoDTO.builder().withCategoria(Objects.nonNull(filtro.getIdCategoria()) ?
                        categoriaService.recuperarCategoriaPorId(filtro.getIdCategoria()) : null).withReceita(receita1)
                .withDespesa(despesa1).withSaldo(receita1.abs().subtract(despesa1.abs())).build();
    }

    private void somarValoresReceitaDespesa(List<LancamentoDTO> lancamentoDTOS, AtomicReference<BigDecimal> receita,
            AtomicReference<BigDecimal> despesa) {
        lancamentoDTOS.forEach(lancamentoDTO -> {
            final BigDecimal valor = lancamentoDTO.getValor();
            if (valor.signum() == 1)
                receita.getAndAccumulate(valor, BigDecimal::add);
            else
                despesa.getAndAccumulate(valor, BigDecimal::add);
        });
    }

}
