package br.com.aann.financeiro.service;

import br.com.aann.financeiro.dto.SubcategoriaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubcategoriaService {

    List<SubcategoriaDTO> recuperarSubcategorias(String nome);

    SubcategoriaDTO criarSubcategoria(SubcategoriaDTO categoriaDTO);

    SubcategoriaDTO editarSubcategoria(SubcategoriaDTO subcategoriaDTO);

    Boolean excluirSubcategoria(Long idCategoria);

    SubcategoriaDTO recuperarSubcategoriaPorId(Long idSubcategoria);
}
