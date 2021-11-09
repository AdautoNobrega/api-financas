package br.com.aann.financeiro.repository;

import br.com.aann.financeiro.model.SubcategoriaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends BaseRepository<SubcategoriaEntity, Long> {

    @Override
    @EntityGraph(value = "sub-all", type = EntityGraph.EntityGraphType.FETCH)
    SubcategoriaEntity getById(Long id);
}