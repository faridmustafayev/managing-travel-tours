package az.ingress.tour.management.service.abstraction;

import az.ingress.tour.management.dao.entity.TourEntity;
import az.ingress.tour.management.model.request.GuideRequest;
import az.ingress.tour.management.model.response.GuideResponse;
import az.ingress.tour.management.model.request.TourRequest;

import java.time.LocalDate;
import java.util.List;

public interface GuideService {
    void saveGuide(GuideRequest request);

    void saveGuideForTour(TourEntity tour, GuideRequest request);

    void saveTourToGuide(Long guideId, TourRequest tourRequest);

    List<GuideResponse> getGuidesByTourId(Long tourId);

    List<GuideResponse> getGuidesNotAssignedToTour(LocalDate startDate, LocalDate endDate);
}
