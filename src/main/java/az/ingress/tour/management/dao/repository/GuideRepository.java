package az.ingress.tour.management.dao.repository;

import az.ingress.tour.management.dao.entity.GuideEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GuideRepository extends CrudRepository<GuideEntity, Long> {

    @Query("SELECT t.guides FROM TourEntity t WHERE t.id = :tourId")
    List<GuideEntity> findGuideByTourId(@Param("tourId") Long tourId);

    @Query("SELECT g FROM GuideEntity g WHERE g NOT IN " +
            "(SELECT guide FROM TourEntity t JOIN t.guides guide WHERE " +
            "t.startDate < :endDate AND t.endDate > :startDate)")
    List<GuideEntity> findGuideWhoNotAssignedToTour(@Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);

}
