package br.com.aann.financeiro.repository.specs;

import org.springframework.data.jpa.domain.Specification;

public class BaseSpecification {

    public static <E> Specification<E> addClausula(Specification<E> where, Specification<E> novaClausula) {
        if (where == null) {
            return Specification.where(novaClausula);
        } else {
            return where.and(novaClausula);
        }
    }
}
