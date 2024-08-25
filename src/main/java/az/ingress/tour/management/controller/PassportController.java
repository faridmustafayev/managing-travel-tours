package az.ingress.tour.management.controller;

import az.ingress.tour.management.model.response.PassportResponse;
import az.ingress.tour.management.service.abstraction.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/passports")
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    @GetMapping("/{guideId}")
    public PassportResponse getPassportByGuideId(@PathVariable Long guideId){
        return passportService.getPassportByGuideId(guideId);
    }
}
