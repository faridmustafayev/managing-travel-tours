package az.ingress.tour.management.controller;

import az.ingress.tour.management.model.request.GuideRequest;
import az.ingress.tour.management.model.request.TourRequest;
import az.ingress.tour.management.model.response.GuideResponse;
import az.ingress.tour.management.service.abstraction.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/guides")
@RequiredArgsConstructor
public class GuideController {
    private final GuideService guideService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveGuide(@RequestBody GuideRequest request){
        guideService.saveGuide(request);
    }

    @PostMapping("/{guideId}")
    @ResponseStatus(CREATED)
    public void saveTourToGuide(@PathVariable Long guideId,
                                @RequestBody TourRequest tourRequest){
        guideService.saveTourToGuide(guideId, tourRequest);
    }

    @GetMapping("/{tourId}")
    public List<GuideResponse> getGuidesByTourId(@PathVariable Long tourId){
        return guideService.getGuidesByTourId(tourId);
    }

    @GetMapping
    public List<GuideResponse> getGuidesNotAssignedToTour(@RequestParam LocalDate startDate,
                                                          @RequestParam LocalDate endDate){
        return guideService.getGuidesNotAssignedToTour(startDate, endDate);
    }
}
