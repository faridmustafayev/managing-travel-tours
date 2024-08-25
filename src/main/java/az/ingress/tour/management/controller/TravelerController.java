package az.ingress.tour.management.controller;

import az.ingress.tour.management.model.request.TravelerRequest;
import az.ingress.tour.management.model.response.TravelerResponse;
import az.ingress.tour.management.service.abstraction.TravelerService;
import az.ingress.tour.management.model.request.TourRequest;
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

@RestController
@RequestMapping("v1/travelers")
@RequiredArgsConstructor
public class TravelerController {
    private final TravelerService travelerService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveTraveler(@RequestBody TravelerRequest traveler){
        travelerService.saveTraveler(traveler);
    }

    @GetMapping("/{tourId}")
    public List<TravelerResponse> getTravelersByTourId(@PathVariable Long tourId){
        return travelerService.getTravelersByTourId(tourId);
    }

    @PostMapping("/{travelerId}")
    @ResponseStatus(CREATED)
    public void saveTourToTraveler(@PathVariable Long travelerId,
                                   @RequestBody TourRequest tourRequest){
        travelerService.saveTourToTraveler(travelerId, tourRequest);
    }
}
