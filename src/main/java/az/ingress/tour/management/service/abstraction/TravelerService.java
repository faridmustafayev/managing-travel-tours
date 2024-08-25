package az.ingress.tour.management.service.abstraction;

import az.ingress.tour.management.model.request.TourRequest;
import az.ingress.tour.management.model.request.TravelerRequest;
import az.ingress.tour.management.model.response.TravelerResponse;

import java.util.List;

public interface TravelerService {
    void saveTraveler(TravelerRequest traveler);

    void saveTravelerToTour(Long tourId, TravelerRequest traveler);

    List<TravelerResponse> getTravelersByTourId(Long tourId);

    void saveTourToTraveler(Long travelerId, TourRequest tourRequest);
}
