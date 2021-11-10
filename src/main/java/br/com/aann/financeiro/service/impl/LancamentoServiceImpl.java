package br.com.aann.financeiro.service.impl;

import br.com.aann.financeiro.dto.LancamentoDTO;
import br.com.aann.financeiro.filter.BalancoFilter;
import br.com.aann.financeiro.mapper.LancamentoMapper;
import br.com.aann.financeiro.model.LancamentoEntity;
import br.com.aann.financeiro.model.SubcategoriaEntity;
import br.com.aann.financeiro.repository.LancamentoRepository;
import br.com.aann.financeiro.repository.specs.BaseSpecification;
import br.com.aann.financeiro.repository.specs.LancamentoSpecification;
import br.com.aann.financeiro.service.LancamentoService;
import br.com.aann.financeiro.service.SubcategoriaService;
import br.com.aann.financeiro.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.aann.financeiro.repository.specs.BaseSpecification.addClausula;

@Slf4j
@Service
@AllArgsConstructor
public class LancamentoServiceImpl implements LancamentoService {

    private final SubcategoriaService subcategoriaService;
    private final LancamentoRepository lancamentoRepository;
    private final LancamentoMapper lancamentoMapper;

    @Override
    public List<LancamentoDTO> recuperarLancamentos() {
        return lancamentoMapper.mapToList(lancamentoRepository.findAll());
    }

    @Override
    public List<LancamentoDTO> recuperarLancamentosPorFiltros(BalancoFilter filtro) {
        Specification<LancamentoEntity> whereData = null;
        if (Objects.nonNull(filtro.getDataInicio())) {
            final LocalDate dataFim = filtro.getDataFim();
            whereData = LancamentoSpecification.whereData(filtro.getDataInicio(),
                    Objects.isNull(dataFim) ? LocalDate.now() : dataFim);
        }
        if (Objects.nonNull(filtro.getIdCategoria()))
            whereData = addClausula(whereData, LancamentoSpecification.whereIdCategoria(filtro.getIdCategoria()));
        return lancamentoMapper.mapToList(lancamentoRepository.findAll(whereData));
    }

    @Override
    public LancamentoDTO recuperarLancamentoPorId(Long idLancamento) {
        return recuperarLancamentoEntityPorId(idLancamento).map(lancamentoMapper::map).orElse(null);
    }

    private Optional<LancamentoEntity> recuperarLancamentoEntityPorId(Long idLancamento) {
        return lancamentoRepository.findById(idLancamento);
    }

    @Override
    public LancamentoDTO criarLancamento(LancamentoDTO lancamentoDTO) {
        this.validarLancamento(lancamentoDTO);
        final LancamentoEntity lancamentoEntity = lancamentoRepository.save(
                lancamentoMapper.map(lancamentoDTO));
        lancamentoDTO.setId(lancamentoEntity.getId());
        return lancamentoDTO;
    }

    @Override
    public LancamentoDTO editarLancamento(LancamentoDTO lancamentoDTO) {
        final LancamentoEntity lancamento = this.recuperarLancamentoEntityPorId(lancamentoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado o lançamento"));
        if (Objects.nonNull(lancamentoDTO.getValor()))
            lancamento.setValor(lancamentoDTO.getValor());
        if (Objects.nonNull(lancamentoDTO.getIdSubcategoria()))
            lancamento.setSubcategoria(new SubcategoriaEntity(lancamentoDTO.getIdSubcategoria()));
        if (StringUtil.naoEstaVazio(lancamentoDTO.getComentario()))
            lancamento.setComentario(lancamentoDTO.getComentario());
        return lancamentoMapper.map(lancamentoRepository.save(lancamento));
    }

    @Override
    public Boolean excluirLancamento(Long idLancamento) {
        try {
            Objects.requireNonNull(idLancamento);
            this.lancamentoRepository.deleteById(idLancamento);
            return true;
        } catch (Exception ex) {
            log.error("Não foi possível excluir o lançamento. Erro: {}", ex.getLocalizedMessage(), ex);
            return false;
        }
    }

    private void validarLancamento(LancamentoDTO lancamentoDTO) {
        Objects.requireNonNull(lancamentoDTO.getValor(), "O valor não pode ser nulo");
        Objects.requireNonNull(lancamentoDTO.getIdSubcategoria(), "A subcategoria precisa ser informada");
        Objects.requireNonNull(subcategoriaService.recuperarSubcategoriaPorId(lancamentoDTO.getIdSubcategoria()));
    }
}
