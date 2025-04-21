package br.com.fiap.empreenda_api.specification;

import br.com.fiap.empreenda_api.model.Sale;
import br.com.fiap.empreenda_api.model.SaleFilters;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SaleSpecification {

    public static Specification<Sale> withFilters(SaleFilters filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
    
            if (filters.cliente() != null && !filters.cliente().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("cliente")), "%" + filters.cliente().toLowerCase() + "%"));
            }
    
            if (filters.valorMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("valorTotal"), filters.valorMin()));
            }
    
            if (filters.valorMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("valorTotal"), filters.valorMax()));
            }
    
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }    
}