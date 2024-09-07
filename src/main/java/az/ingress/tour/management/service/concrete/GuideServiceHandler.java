package az.ingress.tour.management.service.concrete;

import az.ingress.tour.management.dao.entity.GuideEntity;
import az.ingress.tour.management.dao.entity.TourEntity;
import az.ingress.tour.management.exception.NotFoundException;
import az.ingress.tour.management.exception.ExceptionMessage;
import az.ingress.tour.management.dao.repository.GuideRepository;
import az.ingress.tour.management.dao.repository.TourRepository;
import az.ingress.tour.management.model.request.GuideRequest;
import az.ingress.tour.management.model.request.TourRequest;
import az.ingress.tour.management.model.response.GuideResponse;
import az.ingress.tour.management.service.abstraction.GuideService;
import az.ingress.tour.management.service.abstraction.PassportService;
import az.ingress.tour.management.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.tour.management.mapper.GuideMapper.GUIDE_MAPPER;
import static az.ingress.tour.management.mapper.TourMapper.TOUR_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideServiceHandler implements GuideService {
    private final GuideRepository guideRepository;
    private final TourRepository tourRepository;
    private final PassportService passportService;
    private TourService tourService;

    @Autowired
    public void setTourService(@Lazy TourService tourService) {
        this.tourService = tourService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveGuide(GuideRequest request) {
        log.info("ActionLog.saveGuide.start");
        GuideEntity guide = GUIDE_MAPPER.buildGuideEntity(request);
        guideRepository.save(guide);
        passportService.savePassport(guide, request.getPassportInfo());
        log.info("ActionLog.saveGuide.success");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveGuideForTour(TourEntity tour, GuideRequest request) {
        log.info("ActionLog.saveGuideForTour.start with id: {}", tour.getId());
        GuideEntity guideEntity = GUIDE_MAPPER.buildGuideEntity(request);

        List<GuideEntity> guides = tour.getGuides();
        guides.add(guideEntity);

        tour.setGuides(guides);

        guideRepository.save(guideEntity);
        passportService.savePassport(guideEntity, request.getPassportInfo());
        log.info("ActionLog.saveGuideForTour.success with id: {}", tour.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTourToGuide(Long guideId, TourRequest tourRequest) {
        log.info("ActionLog.saveGuideToTour.start with guideId: {}", guideId);
        GuideEntity guide = fetchGuideIfExist(guideId);
        TourEntity tour = TOUR_MAPPER.buildTourEntity(tourRequest);

        List<GuideEntity> guides = tour.getGuides();
        if (guides == null) {
            guides = new ArrayList<>();
        }
        guides.add(guide);

        List<TourEntity> overlappingTours = tourRepository.findOverlappingTours(guideId, tour.getStartDate(), tour.getEndDate());
        if (!overlappingTours.isEmpty()) {
            throw new IllegalArgumentException("The guide is already assigned to another tour during the specified period.");
        }

        tour.setGuides(guides);

        tourRepository.save(tour);
        log.info("ActionLog.saveGuideToTour.success with guideId: {}", guideId);
    }

    @Cacheable("getGuidesSpecificTourId")
    @Override
    public List<GuideResponse> getGuidesByTourId(Long tourId) {
        log.info("ActionLog.getGuidesSpecificTourId.start with tourId: {}", tourId);
        List<GuideEntity> guides = guideRepository.findGuideByTourId(tourId);
        log.info("ActionLog.getGuidesSpecificTourId.success with tourId: {}", tourId);

        return guides.stream()
                .map(GUIDE_MAPPER::buildGuideResponse)
                .collect(Collectors.toList());
    }

    @Cacheable("getGuidesNotAssignedToTour")
    @Override
    public List<GuideResponse> getGuidesNotAssignedToTour(LocalDate startDate, LocalDate endDate) {
        log.info("ActionLog.getGuidesNotAssignedToTour.start with startDate and endDate: {}, {}", startDate, endDate);
        List<GuideEntity> guides = guideRepository.findGuideWhoNotAssignedToTour(startDate, endDate);
        log.info("ActionLog.getGuidesNotAssignedToTour.success with startDate and endDate: {}, {}", startDate, endDate);
        return guides.stream()
                .map(GUIDE_MAPPER::buildGuideResponse)
                .collect(Collectors.toList());
    }

    private GuideEntity fetchGuideIfExist(Long guideId) {
        return guideRepository.findById(guideId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.GUIDE_NOT_FOUND.getCode(), ExceptionMessage.GUIDE_NOT_FOUND.getMessage()));
    }
}
