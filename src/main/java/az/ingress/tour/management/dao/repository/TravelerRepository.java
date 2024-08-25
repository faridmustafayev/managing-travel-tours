package az.ingress.tour.management.dao.repository;

import az.ingress.tour.management.dao.entity.TravelerEntity;
import org.springframework.data.repository.CrudRepository;

public interface TravelerRepository extends CrudRepository<TravelerEntity, Long> {

}
