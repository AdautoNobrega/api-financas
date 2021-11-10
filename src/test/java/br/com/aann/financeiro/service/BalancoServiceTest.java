package br.com.aann.financeiro.service;

import br.com.aann.financeiro.dto.BalancoDTO;
import br.com.aann.financeiro.filter.BalancoFilter;
import br.com.aann.financeiro.mock.utils.MockUtils;
import br.com.aann.financeiro.service.impl.BalancoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BalancoServiceTest {

    BalancoServiceImpl balancoService;
    @Mock
    LancamentoService lancamentoService;
    @Mock
    CategoriaService categoriaService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.balancoService = new BalancoServiceImpl(lancamentoService, categoriaService);
        this.mock();
    }

    private void mock() {
        when(categoriaService.recuperarCategoriaPorId(1L)).thenReturn(MockUtils.getCategoriaDTOMock());
        when(lancamentoService.recuperarLancamentosPorFiltros(any(BalancoFilter.class))).thenReturn(
                Arrays.asList(MockUtils.getLancamentoDTOMock(1L, 1L, false),
                        MockUtils.getLancamentoDTOMock(2L, 1L, false), MockUtils.getLancamentoDTOMock(3L, 1L, false),
                        MockUtils.getLancamentoDTOMock(4L, 1L, true)));
    }

    @Test
    void deveConsultarOBalanco() {
        final BalancoDTO balancoDTO = balancoService.recuperarBalanco(
                BalancoFilter.builder().withDataInicio(LocalDate.of(2021, 1, 1)).withDataFim(LocalDate.of(2021, 12, 31))
                        .withIdCategoria(1L).build());
        Assertions.assertNotEquals(BigDecimal.ZERO, balancoDTO.getSaldo());
        Assertions.assertNotNull(balancoDTO.getCategoria());
    }
}
