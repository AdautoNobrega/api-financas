package br.com.aann.financeiro.service;


import br.com.aann.financeiro.dto.CategoriaDTO;
import br.com.aann.financeiro.mapper.CategoriaMapper;
import br.com.aann.financeiro.model.CategoriaEntity;
import br.com.aann.financeiro.repository.CategoriaRepository;
import br.com.aann.financeiro.service.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.aann.financeiro.mock.utils.MockUtils.getCategoriaDTOMock;
import static br.com.aann.financeiro.mock.utils.MockUtils.getCategoriaEntityMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoriaServiceTest {

    CategoriaServiceImpl categoriaService;
    @Mock
    CategoriaMapper categoriaMapper;
    @Mock
    CategoriaRepository categoriaRepository;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.categoriaService = new CategoriaServiceImpl(this.categoriaMapper, this.categoriaRepository);
        mock();
    }

    private void mock() {
        when(categoriaRepository.save(Mockito.any(CategoriaEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        when(categoriaMapper.map(any(CategoriaDTO.class))).thenAnswer(i -> {
            var categoriaDTO = (CategoriaDTO) i.getArguments()[0];
            return new CategoriaEntity(1L, categoriaDTO.getNome());
        });
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(getCategoriaEntityMock()));
        when(categoriaRepository.getById(1L)).thenReturn(getCategoriaEntityMock());
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(getCategoriaEntityMock()));
        Mockito.doNothing().when(categoriaRepository).delete(any(CategoriaEntity.class));
    }

    @Test
    void deveCriarUmaCategoria() {
        final CategoriaDTO categoriaDTOMock = getCategoriaDTOMock();
        categoriaDTOMock.setId(null);
        final CategoriaDTO categoriaDTO = categoriaService.criarCategoria(categoriaDTOMock);
        Assertions.assertNotNull(categoriaDTO.getId());
        Assertions.assertNotNull(categoriaDTO.getNome());
    }

    @Test
    void deveDarErroAoEditarUmaCategoria() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> categoriaService.editarCategoria(CategoriaDTO.builder().withId(2L).build()));
    }

    @Test
    void deveEditarUmaCategoria() {
        final CategoriaDTO categoriaDTOMock = getCategoriaDTOMock();
        categoriaDTOMock.setNome("Transportes 123");
        final CategoriaDTO categoriaDTO = categoriaService.editarCategoria(categoriaDTOMock);
        Assertions.assertNotNull(categoriaDTO.getNome());
    }

    @Test
    void deveRecuperarAsCategorias() {
        final List<CategoriaDTO> categoriaDTOList = categoriaService.recuperarCategorias(null);
        Assertions.assertFalse(categoriaDTOList.isEmpty());
    }

    @Test
    void deveExcluirAsCategorias() {
        Assertions.assertTrue(categoriaService.excluirCategoria(1L));
    }

}
