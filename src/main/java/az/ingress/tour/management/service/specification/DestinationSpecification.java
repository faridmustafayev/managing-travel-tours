package az.ingress.tour.management.service.specification;

import az.ingress.tour.management.dao.entity.DestinationEntity;
import az.ingress.tour.management.model.criteria.DestinationCriteria;
import az.ingress.tour.management.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static az.ingress.tour.management.dao.entity.DestinationEntity.*;
import static az.ingress.tour.management.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class DestinationSpecification implements Specification<DestinationEntity> {
    private DestinationCriteria destinationCriteria;

    @Override
    public Predicate toPredicate(Root<DestinationEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        Predicate[] predicates = PredicateUtil.builder()
                .addNullSafety(destinationCriteria.getLocation(),
                        location -> criteriaBuilder.like(root.get(Fields.location), applyLikePattern(location)))
                .addNullSafety(destinationCriteria.getVisitDate(),
                        visitDate -> criteriaBuilder.greaterThanOrEqualTo(root.get(Fields.visitDate), visitDate))
                .build();

        return criteriaBuilder.and(predicates);
    }
}
