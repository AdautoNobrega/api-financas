package br.com.aann.financeiro.repository.specs;

import br.com.aann.financeiro.model.CategoriaEntity;
import br.com.aann.financeiro.model.CategoriaEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategoriaSpecification extends BaseSpecification {

    public static Specification<CategoriaEntity> whereNomeLike(String nome) {
        return (root, cq, cb) -> cb.like(cb.upper(root.get(CategoriaEntity_.nome)), nome.toUpperCase());
    }
}
