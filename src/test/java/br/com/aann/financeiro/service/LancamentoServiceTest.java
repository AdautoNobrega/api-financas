package br.com.aann.financeiro.service;

import br.com.aann.financeiro.dto.LancamentoDTO;
import br.com.aann.financeiro.filter.BalancoFilter;
import br.com.aann.financeiro.mapper.LancamentoMapper;
import br.com.aann.financeiro.model.LancamentoEntity;
import br.com.aann.financeiro.repository.LancamentoRepository;
import br.com.aann.financeiro.service.impl.LancamentoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static br.com.aann.financeiro.mock.utils.MockUtils.getSubcategoriaDTOMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LancamentoServiceTest {

    LancamentoServiceImpl lancamentoService;
    @Mock
    SubcategoriaService subcategoriaService;
    @Mock
    LancamentoRepository lancamentoRepository;
    @Mock
    LancamentoMapper lancamentoMapper;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.lancamentoService = new LancamentoServiceImpl(subcategoriaService, lancamentoRepository, lancamentoMapper);
        mock();
    }

    private void mock() {
        when(subcategoriaService.recuperarSubcategoriaPorId(1L)).thenReturn(getSubcategoriaDTOMock());

        final LancamentoDTO lancamentoDTOMockFirst = getLancamentoDTOMock(1L, 1L);
        final LancamentoDTO lancamentoDTOMockSecond = getLancamentoDTOMock(2L, 1L);
        final List<LancamentoDTO> lancamentoDTOList = Arrays.asList(lancamentoDTOMockFirst, lancamentoDTOMockSecond);
        final List<LancamentoEntity> lancamentoEntityList = lancamentoDTOList.stream()
                .map(this::getLancamentoEntityMock).collect(Collectors.toList());
        final LancamentoEntity lancamento = lancamentoEntityList.get(0);
        when(lancamentoRepository.save(any(LancamentoEntity.class))).thenReturn(lancamento);
        when(lancamentoRepository.findAll()).thenReturn(lancamentoEntityList);
        when(lancamentoRepository.findAll(any(Specification.class))).thenReturn(lancamentoEntityList);
        when(lancamentoRepository.findById(1L)).thenReturn(Optional.of(lancamento));
        doNothing().when(lancamentoRepository).deleteById(anyLong());
        when(lancamentoMapper.map(any(LancamentoEntity.class))).thenReturn(lancamentoDTOMockFirst);
        when(lancamentoMapper.map(any(LancamentoDTO.class))).thenReturn(lancamento);
        when(lancamentoMapper.mapToList(anyList())).thenReturn(lancamentoDTOList);
    }

    @Test
    void deveCadastrarLancamento() {
        final LancamentoDTO lancamentoDTO = lancamentoService.criarLancamento(getLancamentoDTOMock(1L, 1L));
        Assertions.assertNotNull(lancamentoDTO);
    }

    @Test
    void deveRecuperarLancamentos() {
        final List<LancamentoDTO> lancamentoDTOS = lancamentoService.recuperarLancamentos();
        Assertions.assertFalse(lancamentoDTOS.isEmpty());
        lancamentoDTOS.forEach(Assertions::assertNotNull);
    }

    @Test
    void deveRecuperarLancamentoPorFiltro() {
        final List<LancamentoDTO> lancamentoDTOS = lancamentoService.recuperarLancamentosPorFiltros(
                BalancoFilter.builder().withDataInicio(LocalDate.of(2021, 1, 1)).withDataFim(LocalDate.of(2021, 12, 31))
                        .withIdCategoria(1L).build());
        Assertions.assertFalse(lancamentoDTOS.isEmpty());
    }

    @Test
    void deveRecuperarLancamentoPorId() {
        final LancamentoDTO lancamentoDTO = lancamentoService.recuperarLancamentoPorId(1L);
        Assertions.assertNotNull(lancamentoDTO.getValor());
        Assertions.assertNotNull(lancamentoDTO.getData());
    }

    @Test
    void deveEditarLancamento() {
        final LancamentoDTO lancamentoDTOMock = getLancamentoDTOMock(1L, 1L);
        final BigDecimal valorNovo = BigDecimal.valueOf(new Random().nextDouble());
        lancamentoDTOMock.setValor(valorNovo);
        final LancamentoDTO lancamentoDTO = lancamentoService.editarLancamento(lancamentoDTOMock);
        Assertions.assertNotNull(lancamentoDTO);
    }

    @Test
    void deveExcluirLancamento() {
        Assertions.assertTrue(lancamentoService.excluirLancamento(1L));
    }

    @Test
    void deveRetornarFalsoCasoNaoAcheOLancamento() {
        Assertions.assertFalse(lancamentoService.excluirLancamento(null));
    }

    private LancamentoDTO getLancamentoDTOMock(long id, long idSubcategoria) {
        final Random random = new Random();
        return LancamentoDTO.builder().withId(id).withValor(BigDecimal.valueOf(random.nextDouble()))
                .withComentario("Teste_" + random.nextInt())
                .withIdSubcategoria(idSubcategoria).withData(LocalDate.of(2021, recuperarNumeroAleatorioAte(random, 12),
                        recuperarNumeroAleatorioAte(random, 30))).build();
    }

    private int recuperarNumeroAleatorioAte(Random random, int i) {
        return random.nextInt(i - 1) + 1;
    }

    private LancamentoEntity getLancamentoEntityMock(LancamentoDTO lancamentoDTOMock) {
        final LancamentoEntity lancamentoEntity = new LancamentoEntity();
        BeanUtils.copyProperties(lancamentoDTOMock, lancamentoEntity);
        return lancamentoEntity;
    }

}
