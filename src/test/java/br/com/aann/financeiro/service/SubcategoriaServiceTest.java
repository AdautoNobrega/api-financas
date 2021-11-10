package br.com.aann.financeiro.service;

import br.com.aann.financeiro.dto.SubcategoriaDTO;
import br.com.aann.financeiro.mapper.SubcategoriaMapper;
import br.com.aann.financeiro.model.SubcategoriaEntity;
import br.com.aann.financeiro.repository.SubcategoriaRepository;
import br.com.aann.financeiro.service.impl.CategoriaServiceImpl;
import br.com.aann.financeiro.service.impl.SubcategoriaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.aann.financeiro.mock.utils.MockUtils.getCategoriaDTOMock;
import static br.com.aann.financeiro.mock.utils.MockUtils.getCategoriaEntityMock;
import static br.com.aann.financeiro.mock.utils.MockUtils.getSubcategoriaDTOMock;
import static br.com.aann.financeiro.mock.utils.MockUtils.getSubcategoriaEntityMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubcategoriaServiceTest {

    public static final String NOME_SUBCATEGORIA = "Uber";

    SubcategoriaServiceImpl subcategoriaService;
    @Mock
    CategoriaServiceImpl categoriaService;
    @Mock
    SubcategoriaRepository subcategoriaRepository;
    @Mock
    SubcategoriaMapper subcategoriaMapper;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.subcategoriaService = new SubcategoriaServiceImpl(categoriaService, subcategoriaRepository,
                subcategoriaMapper);
        mock();
    }

    private void mock() {
        when(categoriaService.recuperarCategoriaPorId(anyLong())).thenReturn(getCategoriaDTOMock());

        final SubcategoriaEntity subcategoriaEntityMock = getSubcategoriaEntityMock();
        final List<SubcategoriaEntity> subcategoriaEntityList = Collections.singletonList(subcategoriaEntityMock);

        when(subcategoriaRepository.save(any(SubcategoriaEntity.class))).thenReturn(subcategoriaEntityMock);
        when(subcategoriaRepository.findAll()).thenReturn(subcategoriaEntityList);
        when(subcategoriaRepository.findAll(any(Specification.class))).thenReturn(
                subcategoriaEntityList);
        when(subcategoriaRepository.findById(1L)).thenReturn(Optional.of(subcategoriaEntityMock));
        doNothing().when(subcategoriaRepository).delete(any(SubcategoriaEntity.class));
        when(subcategoriaRepository.existsById(1L)).thenReturn(true);
        when(subcategoriaRepository.getById(1L)).thenReturn(subcategoriaEntityMock);
        when(subcategoriaMapper.map(any(SubcategoriaEntity.class))).thenReturn(getSubcategoriaDTOMock());
        when(subcategoriaMapper.map(any(SubcategoriaDTO.class))).thenReturn(subcategoriaEntityMock);
    }

    @Test
    @Order(1)
    void deveCriarSubcategoria() {
        final SubcategoriaDTO subcategoriaDTO = subcategoriaService.criarSubcategoria(getSubcategoriaDTOMock());
        Assertions.assertNotNull(subcategoriaDTO.getId());
        Assertions.assertNotNull(subcategoriaDTO.getNome());
    }

    @Test
    @Order(2)
    void deveConsultarCategorias() {
        final List<SubcategoriaDTO> subcategoriaDTOS = subcategoriaService.recuperarSubcategorias(null);
        Assertions.assertFalse(subcategoriaDTOS.isEmpty());
    }

    @Test
    @Order(3)
    void deveConsultarCategoriaPorId() {
        final SubcategoriaDTO subcategoriaDTO = subcategoriaService.recuperarSubcategoriaPorId(1L);
        Assertions.assertNotNull(subcategoriaDTO);
        Assertions.assertEquals(1L, subcategoriaDTO.getId());
    }

    @Test
    @Order(4)
    void deveConsultarCategoriaPorNome() {
        final List<SubcategoriaDTO> subcategoriaDTOS = subcategoriaService.recuperarSubcategorias(NOME_SUBCATEGORIA);
        Assertions.assertFalse(subcategoriaDTOS.isEmpty());
    }

    @Test
    @Order(5)
    void deveEditarSubcategoria() {
        final SubcategoriaDTO subcategoriaDTOMock = getSubcategoriaDTOMock();
        subcategoriaDTOMock.setNome("Novo nome");
        final SubcategoriaDTO subcategoriaDTO = subcategoriaService.editarSubcategoria(subcategoriaDTOMock);
        Assertions.assertEquals("Novo nome", subcategoriaDTO.getNome());
    }

    @Order(6)
    @Test
    void deveDarErroAoCriarSubcategoria() {
        when(subcategoriaRepository.exists(any(Specification.class))).thenReturn(true);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> subcategoriaService.criarSubcategoria(getSubcategoriaDTOMock()));
    }

    @Test
    @Order(7)
    void deveExcluirSubcategoria() {
        Assertions.assertTrue(subcategoriaService.excluirSubcategoria(1L));
    }

}
