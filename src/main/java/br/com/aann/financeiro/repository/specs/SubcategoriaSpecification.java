package br.com.aann.financeiro.repository.specs;

import br.com.aann.financeiro.model.CategoriaEntity_;
import br.com.aann.financeiro.model.SubcategoriaEntity;
import br.com.aann.financeiro.model.SubcategoriaEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SubcategoriaSpecification extends BaseSpecification {

    public static Specification<SubcategoriaEntity> whereNome(String nome) {
        return (root, cq, cb) -> cb.equal(cb.upper(root.get(SubcategoriaEntity_.nome)), nome.toUpperCase());
    }

    public static Specification<SubcategoriaEntity> whereIdCategoria(Long idCategoria) {
        return (root, cq, cb) -> cb.equal(root.get(SubcategoriaEntity_.categoria).get(CategoriaEntity_.id),
                idCategoria);
    }
}
