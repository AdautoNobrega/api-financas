package br.com.aann.financeiro.service.impl;

import br.com.aann.financeiro.dto.CategoriaDTO;
import br.com.aann.financeiro.mapper.CategoriaMapper;
import br.com.aann.financeiro.model.CategoriaEntity;
import br.com.aann.financeiro.model.SubcategoriaEntity;
import br.com.aann.financeiro.repository.CategoriaRepository;
import br.com.aann.financeiro.repository.specs.CategoriaSpecification;
import br.com.aann.financeiro.service.CategoriaService;
import br.com.aann.financeiro.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.aann.financeiro.util.StringUtil.naoEstaVazio;

@Slf4j
@AllArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> recuperarCategorias(String nome) {
        final List<CategoriaEntity> categoriaEntities;
        if (naoEstaVazio(nome))
            categoriaEntities = categoriaRepository.findAll(CategoriaSpecification.whereNomeLike(nome));
        else
            categoriaEntities = categoriaRepository.findAll();
        return categoriaEntities.stream().map(categoriaMapper::map).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO recuperarCategoriaPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria).map(categoriaMapper::map).orElse(null);
    }

    @Override
    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {
        validarCriacao(categoriaDTO);
        if (Objects.nonNull(categoriaDTO.getId()))
            return this.editarCategoria(categoriaDTO);
        final CategoriaEntity categoria = categoriaMapper.map(categoriaDTO);
        final CategoriaEntity novaCategoria = categoriaRepository.save(categoria);
        categoriaDTO.setId(novaCategoria.getId());
        return categoriaDTO;
    }

    private void validarCriacao(CategoriaDTO categoriaDTO) {
        validarNome(categoriaDTO);
        if (categoriaRepository.exists(CategoriaSpecification.whereNomeLike(categoriaDTO.getNome()))) {
            throw new IllegalArgumentException("Já existe essa categoria");
        }
    }

    private void validarNome(CategoriaDTO categoriaDTO) {
        if (StringUtil.estaVazio(categoriaDTO.getNome())) {
            throw new IllegalArgumentException("O nome está vazio");
        }
    }

    @Override
    public CategoriaDTO editarCategoria(CategoriaDTO categoriaDTO) {
        Objects.requireNonNull(categoriaDTO.getId());
        this.validarNome(categoriaDTO);
        final Optional<CategoriaEntity> optionalCategoria = categoriaRepository.findById(categoriaDTO.getId());
        if (!optionalCategoria.isPresent())
            throw new IllegalArgumentException("Id inválido");
        final CategoriaEntity categoria = optionalCategoria.get();
        categoria.setNome(categoriaDTO.getNome());
        categoriaRepository.save(categoria);
        return categoriaDTO;
    }

    @Override
    public Boolean excluirCategoria(Long id) {
        final CategoriaEntity categoria = categoriaRepository.getById(id);
        final Set<SubcategoriaEntity> subcategorias = categoria.getSubcategorias();
        if (CollectionUtils.isEmpty(subcategorias) || subcategorias.stream()
                .allMatch(s -> CollectionUtils.isEmpty(s.getLancamentos()))) {
            categoriaRepository.delete(categoria);
            return true;
        }
        return false;
    }
}
