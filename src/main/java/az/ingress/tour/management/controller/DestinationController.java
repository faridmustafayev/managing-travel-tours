package az.ingress.tour.management.controller;

import az.ingress.tour.management.model.criteria.DestinationCriteria;
import az.ingress.tour.management.model.response.PageableResponse;
import az.ingress.tour.management.model.criteria.PageCriteria;
import az.ingress.tour.management.model.request.DestinationRequest;
import az.ingress.tour.management.model.response.DestinationResponse;
import az.ingress.tour.management.service.abstraction.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/destinations")
@RequiredArgsConstructor
public class DestinationController {
    private final DestinationService destinationService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveDestination(@RequestBody DestinationRequest destination){
        destinationService.saveDestination(destination);
    }

    @GetMapping
    public PageableResponse<DestinationResponse> getDestinations(@RequestParam Long tourId,
                                                                 PageCriteria pageCriteria,
                                                                 DestinationCriteria destinationCriteria){
        return destinationService.getDestinations(tourId, pageCriteria, destinationCriteria);
    }
}
