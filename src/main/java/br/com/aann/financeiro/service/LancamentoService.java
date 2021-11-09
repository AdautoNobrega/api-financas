package br.com.aann.financeiro.service;

import br.com.aann.financeiro.dto.LancamentoDTO;
import br.com.aann.financeiro.filter.BalancoFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LancamentoService {

    List<LancamentoDTO> recuperarLancamentos();

    List<LancamentoDTO> recuperarLancamentosPorFiltros(final BalancoFilter filtro);

    LancamentoDTO recuperarLancamentoPorId(Long idLancamento);

    LancamentoDTO criarLancamento(LancamentoDTO lancamentoDTO);

    LancamentoDTO editarLancamento(LancamentoDTO lancamentoDTO);

    Boolean excluirLancamento(Long idLancamento);
}
