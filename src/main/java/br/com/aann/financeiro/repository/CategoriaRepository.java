package br.com.aann.financeiro.repository;

import br.com.aann.financeiro.model.CategoriaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends BaseRepository<CategoriaEntity, Long> {

    @Override
    @EntityGraph(value = "subcategorias-lancamentos", type = EntityGraph.EntityGraphType.FETCH)
    CategoriaEntity getById(Long id);
}