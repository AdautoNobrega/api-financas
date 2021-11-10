package br.com.aann.financeiro.service.impl;

import br.com.aann.financeiro.dto.CategoriaDTO;
import br.com.aann.financeiro.dto.SubcategoriaDTO;
import br.com.aann.financeiro.mapper.SubcategoriaMapper;
import br.com.aann.financeiro.model.CategoriaEntity;
import br.com.aann.financeiro.model.SubcategoriaEntity;
import br.com.aann.financeiro.repository.SubcategoriaRepository;
import br.com.aann.financeiro.repository.specs.SubcategoriaSpecification;
import br.com.aann.financeiro.service.CategoriaService;
import br.com.aann.financeiro.service.SubcategoriaService;
import br.com.aann.financeiro.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SubcategoriaServiceImpl implements SubcategoriaService {

    private final CategoriaService categoriaService;
    private final SubcategoriaRepository subcategoriaRepository;
    private final SubcategoriaMapper subcategoriaMapper;

    @Override
    public List<SubcategoriaDTO> recuperarSubcategorias(String nome) {
        final List<SubcategoriaEntity> subcategoriaList = StringUtil.naoEstaVazio(nome) ?
                subcategoriaRepository.findAll(SubcategoriaSpecification.whereNome(nome)) :
                subcategoriaRepository.findAll();
        return subcategoriaList.stream().map(subcategoriaMapper::map).collect(Collectors.toList());
    }

    @Override
    public SubcategoriaDTO criarSubcategoria(SubcategoriaDTO subcategoriaDTO) {
        final Long idCategoria = subcategoriaDTO.getIdCategoria();
        validarNomeUnico(idCategoria, subcategoriaDTO.getNome());
        final CategoriaDTO categoriaDTO = categoriaService.recuperarCategoriaPorId(idCategoria);
        if (Objects.nonNull(categoriaDTO)) {
            final SubcategoriaEntity subcategoriaEntity = subcategoriaRepository.save(
                    new SubcategoriaEntity(subcategoriaDTO.getNome(), new CategoriaEntity(idCategoria)));
            subcategoriaDTO.setId(subcategoriaEntity.getId());
        }
        return subcategoriaDTO;
    }

    @Override
    public SubcategoriaDTO editarSubcategoria(SubcategoriaDTO subcategoriaDTO) {
        if (Objects.nonNull(subcategoriaDTO.getId())) {
            final Optional<SubcategoriaEntity> optionalSubcategoria = recuperarSubcategoriaEntityPorId(
                    subcategoriaDTO.getId());
            if (optionalSubcategoria.isPresent()) {
                final SubcategoriaEntity subcategoria = optionalSubcategoria.get();
                if (!subcategoriaDTO.getNome().equals(subcategoria.getNome())) {
                    subcategoria.setNome(subcategoriaDTO.getNome().toUpperCase());
                    subcategoriaRepository.save(subcategoria);
                    return subcategoriaDTO;
                }
            }
        }
        return subcategoriaDTO;
    }

    @Override
    public Boolean excluirSubcategoria(Long idSubcategoria) {
        if (subcategoriaRepository.existsById(idSubcategoria)) {
            final SubcategoriaEntity subcategoria = subcategoriaRepository.getById(idSubcategoria);
            if (CollectionUtils.isEmpty(subcategoria.getLancamentos())) {
                subcategoriaRepository.delete(subcategoria);
                return true;
            }
        }
        return false;
    }

    @Override
    public SubcategoriaDTO recuperarSubcategoriaPorId(Long idSubcategoria) {
        return recuperarSubcategoriaEntityPorId(idSubcategoria).map(subcategoriaMapper::map).orElse(null);
    }

    private Optional<SubcategoriaEntity> recuperarSubcategoriaEntityPorId(Long idSubcategoria) {
        return subcategoriaRepository.findById(idSubcategoria);
    }

    private void validarNomeUnico(Long idCategoria, String nome) {
        final Specification<SubcategoriaEntity> where = SubcategoriaSpecification.whereNome(nome)
                .and(SubcategoriaSpecification.whereIdCategoria(idCategoria));
        if (subcategoriaRepository.exists(where)) {
            throw new IllegalArgumentException("Já existe esta subcategoria nessa categoria");
        }
    }
}
