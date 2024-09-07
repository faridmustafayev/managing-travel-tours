package az.ingress.tour.management.dao.repository;

import az.ingress.tour.management.dao.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DestinationRepository extends JpaRepository<DestinationEntity, Long>, JpaSpecificationExecutor<DestinationEntity> {
}
