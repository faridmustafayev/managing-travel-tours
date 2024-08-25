package az.ingress.tour.management.service.concrete;

import az.ingress.tour.management.dao.entity.TourEntity;
import az.ingress.tour.management.dao.entity.TravelerEntity;
import az.ingress.tour.management.exception.NotFoundException;
import az.ingress.tour.management.exception.ExceptionMessage;
import az.ingress.tour.management.dao.repository.TourRepository;
import az.ingress.tour.management.dao.repository.TravelerRepository;
import az.ingress.tour.management.model.request.TourRequest;
import az.ingress.tour.management.model.request.TravelerRequest;
import az.ingress.tour.management.model.response.TravelerResponse;
import az.ingress.tour.management.service.abstraction.TourService;
import az.ingress.tour.management.service.abstraction.TravelerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.tour.management.mapper.TourMapper.TOUR_MAPPER;
import static az.ingress.tour.management.mapper.TravelerMapper.TRAVELER_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelerServiceHandler implements TravelerService {
    private final TravelerRepository travelerRepository;
    private final TourRepository tourRepository;
    private TourService tourService;

    @Autowired
    public void setTourService(@Lazy TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public void saveTraveler(TravelerRequest traveler) {
        log.info("ActionLog.saveTraveler.start");
        TravelerEntity travelerEntity = TRAVELER_MAPPER.buildTravelerEntity(traveler);
        travelerRepository.save(travelerEntity);
        log.info("ActionLog.saveTraveler.success");
    }

    @Override
    public void saveTravelerToTour(Long id, TravelerRequest traveler) {
        log.info("ActionLog.saveTraveler.start with id: {}", id);
        TravelerEntity travelerEntity = TRAVELER_MAPPER.buildTravelerEntity(traveler);
        TourEntity tour = TOUR_MAPPER.fetchTourIfExist(id, tourRepository);

        List<TravelerEntity> travelers = tour.getTravelers();
        if (travelers == null) {
            travelers = new ArrayList<>();
        }
        travelers.add(travelerEntity);
        tour.setTravelers(travelers);

        travelerRepository.save(travelerEntity);
        log.info("ActionLog.saveTraveler.success with id: {}", id);
    }

    @Cacheable("getTravelers")
    @Override
    public List<TravelerResponse> getTravelersByTourId(Long tourId) {
        log.info("ActionLog.getTravelers.start with tourId: {}", tourId);
        TourEntity tourEntity = TOUR_MAPPER.fetchTourIfExist(tourId, tourRepository);
        List<TravelerEntity> travelers = tourEntity.getTravelers();
        log.info("ActionLog.getTravelers.success with tourId: {}", tourId);

        return travelers.stream()
                .map(TRAVELER_MAPPER::buildTravelerResponse)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTourToTraveler(Long travelerId, TourRequest tourRequest) {
        log.info("ActionLog.addTourToTraveler.start with travelerId: {}", travelerId);
        TravelerEntity traveler = fetchTravelerIfExist(travelerId);
        tourService.saveTour(tourRequest);

        TourEntity tour = TOUR_MAPPER.buildTourEntity(tourRequest);

        List<TravelerEntity> travelers = tour.getTravelers();
        if (travelers == null) {
            travelers = new ArrayList<>();
        }
        travelers.add(traveler);

        tour.setTravelers(travelers);

        tourRepository.save(tour);
        log.info("ActionLog.addTourToTraveler.success with travelerId: {}", travelerId);
    }

    private TravelerEntity fetchTravelerIfExist(Long travelerId) {
        return travelerRepository.findById(travelerId).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.TOUR_NOT_FIND.getCode(), ExceptionMessage.TRAVELER_NOT_FOUND.getMessage()));
    }

}
