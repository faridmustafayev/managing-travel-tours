package az.ingress.tour.management.service.abstraction;

import az.ingress.tour.management.model.request.DestinationRequest;
import az.ingress.tour.management.model.request.GuideRequest;
import az.ingress.tour.management.model.request.TourRequest;
import az.ingress.tour.management.model.request.TravelerRequest;
import az.ingress.tour.management.model.response.TourResponse;

import java.util.List;

public interface TourService {
    void saveTour(TourRequest request);

    void saveDestinationToTour(Long id, DestinationRequest destination);

    void saveGuideToTour(Long id, GuideRequest guide);

    void saveGuideToTourWithId(Long tourId, Long guideId);

    void saveTravelerToTour(Long id, TravelerRequest traveler);

    void saveTravelerToTourWithId(Long tourId, Long travelerId);

    List<TourResponse> getToursByTravelerId(Long travelerId);

}
