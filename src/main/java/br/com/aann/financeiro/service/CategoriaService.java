package br.com.aann.financeiro.service;

import br.com.aann.financeiro.dto.CategoriaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService {

    List<CategoriaDTO> recuperarCategorias(String nome);

    CategoriaDTO recuperarCategoriaPorId(Long idCategoria);

    CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO editarCategoria(CategoriaDTO categoriaDTO);

    Boolean excluirCategoria(Long id);
}
