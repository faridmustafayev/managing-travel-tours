package az.ingress.tour.management.controller;

import az.ingress.tour.management.model.request.GuideRequest;
import az.ingress.tour.management.model.request.TravelerRequest;
import az.ingress.tour.management.model.response.TourResponse;
import az.ingress.tour.management.model.request.DestinationRequest;
import az.ingress.tour.management.model.request.TourRequest;
import az.ingress.tour.management.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/tours")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveTour(@RequestBody TourRequest request) {
        tourService.saveTour(request);
    }

    @PostMapping("/{id}/destination")
    @ResponseStatus(OK)
    public void saveDestinationToTour(@PathVariable Long id,
                                      @RequestBody DestinationRequest destination){
        tourService.saveDestinationToTour(id, destination);
    }

    @PostMapping("/{id}/guide")
    @ResponseStatus(OK)
    public void saveGuideToTour(@PathVariable Long id,
                                @RequestBody GuideRequest guide){
        tourService.saveGuideToTour(id, guide);
    }

    @PostMapping("/{tourId}/{guideId}/guide")
    @ResponseStatus(OK)
    public void saveGuideToTourWithId(@PathVariable Long tourId,
                                      @PathVariable Long guideId){
        tourService.saveGuideToTourWithId(tourId, guideId);
    }

    @PostMapping("/{id}/traveler")
    @ResponseStatus(OK)
    public void saveTravelerToTour(@PathVariable Long id,
                                   @RequestBody TravelerRequest traveler){
        tourService.saveTravelerToTour(id, traveler);
    }

    @PostMapping("/{tourId}/{travelerId}/traveler")
    @ResponseStatus(OK)
    public void saveTravelerToTourWithId(@PathVariable Long tourId,
                                         @PathVariable Long travelerId){
        tourService.saveTravelerToTourWithId(tourId, travelerId);
    }

    @GetMapping("/{travelerId}")
    public List<TourResponse> getToursByTravelerId(@PathVariable Long travelerId){
        return tourService.getToursByTravelerId(travelerId);
    }
}
