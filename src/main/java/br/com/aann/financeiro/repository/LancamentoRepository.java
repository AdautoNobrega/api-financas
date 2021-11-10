package br.com.aann.financeiro.repository;

import br.com.aann.financeiro.model.LancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends BaseRepository<LancamentoEntity, Long> {

}