package br.com.aann.financeiro.mock.utils;

import br.com.aann.financeiro.dto.CategoriaDTO;
import br.com.aann.financeiro.dto.SubcategoriaDTO;
import br.com.aann.financeiro.model.CategoriaEntity;
import br.com.aann.financeiro.model.SubcategoriaEntity;

import java.util.Collections;

public class MockUtils {

    public static final String CATEGORIA = "Transporte";
    private static final String SUBCATEGORIA = "Uber";

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
}
