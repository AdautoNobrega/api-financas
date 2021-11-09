package br.com.aann.financeiro.repository.specs;

import br.com.aann.financeiro.model.CategoriaEntity_;
import br.com.aann.financeiro.model.LancamentoEntity;
import br.com.aann.financeiro.model.LancamentoEntity_;
import br.com.aann.financeiro.model.SubcategoriaEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LancamentoSpecification extends BaseSpecification {

    public static Specification<LancamentoEntity> whereData(LocalDate dataInicio, LocalDate dataFim) {
        return (root, cq, cb) -> cb.between(root.get(LancamentoEntity_.data), dataInicio, dataFim);
    }

    public static Specification<LancamentoEntity> whereIdCategoria(Long idCategoria) {
        return (root, cq, cb) -> cb.equal(
                root.get(LancamentoEntity_.subcategoria).get(SubcategoriaEntity_.categoria).get(
                        CategoriaEntity_.id), idCategoria);
    }

}
