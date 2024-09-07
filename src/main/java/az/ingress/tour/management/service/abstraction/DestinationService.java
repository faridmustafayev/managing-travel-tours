package az.ingress.tour.management.service.abstraction;

import az.ingress.tour.management.dao.entity.TourEntity;
import az.ingress.tour.management.model.criteria.DestinationCriteria;
import az.ingress.tour.management.model.criteria.PageCriteria;
import az.ingress.tour.management.model.request.DestinationRequest;
import az.ingress.tour.management.model.response.DestinationResponse;
import az.ingress.tour.management.model.response.PageableResponse;


public interface DestinationService {
    void saveDestination(DestinationRequest destination);

    void saveDestinationToTour(TourEntity tour, DestinationRequest request);

    PageableResponse<DestinationResponse> getDestinations(Long tourId,
                                                          PageCriteria pageCriteria,
                                                          DestinationCriteria destinationCriteria);
}
