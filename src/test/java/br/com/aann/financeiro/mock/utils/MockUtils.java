package br.com.aann.financeiro.mock.utils;

import br.com.aann.financeiro.dto.CategoriaDTO;
import br.com.aann.financeiro.dto.LancamentoDTO;
import br.com.aann.financeiro.dto.SubcategoriaDTO;
import br.com.aann.financeiro.model.CategoriaEntity;
import br.com.aann.financeiro.model.LancamentoEntity;
import br.com.aann.financeiro.model.SubcategoriaEntity;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Random;

public class MockUtils {

    public static final String CATEGORIA = "Transporte";
    private static final String SUBCATEGORIA = "Uber";
    private MockUtils() {

    }

    public static CategoriaDTO getCategoriaDTOMock() {
        return new CategoriaDTO(1L, CATEGORIA);
    }

    public static CategoriaEntity getCategoriaEntityMock() {
        return new CategoriaEntity(1L, CATEGORIA, Collections.emptySet());
    }

    public static SubcategoriaDTO getSubcategoriaDTOMock() {
        return SubcategoriaDTO.builder().withId(1L).withIdCategoria(1L).withNome(SUBCATEGORIA).build();
    }

    public static SubcategoriaEntity getSubcategoriaEntityMock() {
        return new SubcategoriaEntity(1L, SUBCATEGORIA, getCategoriaEntityMock(), Collections.emptySet());
    }

    public static LancamentoDTO getLancamentoDTOMock(long id, long idSubcategoria, boolean valorPositivo) {
        final Random random = new Random();
        return LancamentoDTO.builder().withId(id).withValor(BigDecimal.valueOf(random.nextDouble() * (valorPositivo ? 1 : -1)))
                .withComentario("Teste_" + random.nextInt())
                .withIdSubcategoria(idSubcategoria).withData(LocalDate.of(2021, recuperarNumeroAleatorioAte(random, 12),
                        recuperarNumeroAleatorioAte(random, 30))).build();
    }

    public static int recuperarNumeroAleatorioAte(Random random, int i) {
        return random.nextInt(i - 1) + 1;
    }

    public static LancamentoEntity getLancamentoEntityMock(LancamentoDTO lancamentoDTOMock) {
        final LancamentoEntity lancamentoEntity = new LancamentoEntity();
        BeanUtils.copyProperties(lancamentoDTOMock, lancamentoEntity);
        return lancamentoEntity;
    }
}
