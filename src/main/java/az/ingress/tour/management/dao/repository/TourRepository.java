package az.ingress.tour.management.dao.repository;

import az.ingress.tour.management.dao.entity.TourEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TourRepository extends CrudRepository<TourEntity, Long> {

    @Query("SELECT t FROM TourEntity t JOIN t.guides g WHERE g.id = :guideId AND t.startDate < :endDate AND t.endDate > :startDate")
    List<TourEntity> findOverlappingTours(@Param("guideId") Long guideId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM TourEntity t JOIN t.travelers tr WHERE tr.id = :travelerId")
    List<TourEntity> findByTravelersId(@Param("travelerId") Long travelerId);
}
