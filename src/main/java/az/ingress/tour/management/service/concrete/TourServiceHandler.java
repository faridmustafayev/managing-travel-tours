package az.ingress.tour.management.service.concrete;

import az.ingress.tour.management.dao.entity.GuideEntity;
import az.ingress.tour.management.dao.entity.TourEntity;
import az.ingress.tour.management.dao.entity.TravelerEntity;
import az.ingress.tour.management.model.request.DestinationRequest;
import az.ingress.tour.management.model.request.GuideRequest;
import az.ingress.tour.management.model.request.TourRequest;
import az.ingress.tour.management.model.request.TravelerRequest;
import az.ingress.tour.management.model.response.TourResponse;
import az.ingress.tour.management.service.abstraction.DestinationService;
import az.ingress.tour.management.service.abstraction.GuideService;
import az.ingress.tour.management.service.abstraction.TravelerService;
import az.ingress.tour.management.dao.repository.GuideRepository;
import az.ingress.tour.management.dao.repository.TourRepository;
import az.ingress.tour.management.dao.repository.TravelerRepository;
import az.ingress.tour.management.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.tour.management.mapper.GuideMapper.GUIDE_MAPPER;
import static az.ingress.tour.management.mapper.TourMapper.TOUR_MAPPER;
import static az.ingress.tour.management.mapper.TravelerMapper.TRAVELER_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceHandler implements TourService {
    private final TourRepository tourRepository;
    private final DestinationService destinationService;
    private final GuideService guideService;
    private final TravelerService travelerService;
    private final GuideRepository guideRepository;
    private final TravelerRepository travelerRepository;

    @Override
    public void saveTour(TourRequest request) {
        log.info("ActionLog.saveTour.start");
        TourEntity tourEntity = TOUR_MAPPER.buildTourEntity(request);
        tourRepository.save(tourEntity);
        log.info("ActionLog.saveTour.success");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveDestinationToTour(Long id, DestinationRequest destination) {
        log.info("ActionLog.addDestinationToTour.start with id: {}", id);
        TourEntity tour = TOUR_MAPPER.fetchTourIfExist(id, tourRepository);
        destinationService.saveDestinationToTour(tour, destination);
        tourRepository.save(tour);
        log.info("ActionLog.addDestinationToTour.success with id: {}", id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveGuideToTour(Long id, GuideRequest guide) {
        log.info("ActionLog.addGuideToTour.start with id: {}", id);
        TourEntity tour = TOUR_MAPPER.fetchTourIfExist(id, tourRepository);
        guideService.saveGuideForTour(tour, guide);
        tourRepository.save(tour);
        log.info("ActionLog.addGuideToTour.success with id: {}", id);
    }

    @Transactional
    @Override
    public void saveGuideToTourWithId(Long tourId, Long guideId) {
        log.info("ActionLog.addGuideToTourWithId.start with tourId, guideId: {}, {}", tourId, guideId);
        TourEntity tour = TOUR_MAPPER.fetchTourIfExist(tourId, tourRepository);
        GuideEntity guide = GUIDE_MAPPER.fetchGuideIfExist(guideId, guideRepository);
        tour.getGuides().add(guide);
        log.info("ActionLog.addGuideToTourWithId.success with tourId, guideId: {}, {}", tourId, guideId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTravelerToTour(Long id, TravelerRequest traveler) {
        log.info("ActionLog.addTravelerToTour.start with id: {}", id);
        TourEntity tour = TOUR_MAPPER.fetchTourIfExist(id, tourRepository);
        travelerService.saveTravelerToTour(tour.getId(), traveler);
        tourRepository.save(tour);
        log.info("ActionLog.addTravelerToTour.success with id: {}", id);
    }

    @Transactional
    @Override
    public void saveTravelerToTourWithId(Long tourId, Long travelerId) {
        log.info("ActionLog.addTravelerToTourWithId.start with tourId, travelerId: {}, {}", tourId, travelerId);
        TourEntity tour = TOUR_MAPPER.fetchTourIfExist(tourId, tourRepository);
        TravelerEntity traveler = TRAVELER_MAPPER.fetchTravelerIfExist(travelerId, travelerRepository);
        tour.getTravelers().add(traveler);
        log.info("ActionLog.addTravelerToTourWithId.success with tourId, travelerId: {}, {}", tourId, travelerId);
    }

    @Cacheable("getToursSpecificTraveler")
    @Override
    public List<TourResponse> getToursByTravelerId(Long travelerId) {
        log.info("ActionLog.getToursSpecificTraveler.start with travelerId: {}", travelerId);
        List<TourEntity> tours = tourRepository.findByTravelersId(travelerId);
        log.info("ActionLog.getToursSpecificTraveler.success with travelerId: {}", travelerId);

        return tours.stream()
                .map(TOUR_MAPPER::buildTourResponse)
                .collect(Collectors.toList());
    }

}
