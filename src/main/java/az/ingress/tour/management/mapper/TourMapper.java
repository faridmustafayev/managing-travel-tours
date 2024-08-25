package az.ingress.tour.management.mapper;

import az.ingress.tour.management.dao.entity.TourDetailEntity;
import az.ingress.tour.management.dao.entity.TourEntity;
import az.ingress.tour.management.exception.NotFoundException;
import az.ingress.tour.management.model.response.TourResponse;
import az.ingress.tour.management.dao.repository.TourRepository;
import az.ingress.tour.management.model.request.TourRequest;

import java.util.stream.Collectors;

import static az.ingress.tour.management.exception.ExceptionMessage.TOUR_NOT_FIND;
import static az.ingress.tour.management.mapper.DestinationMapper.DESTINATION_MAPPER;
import static az.ingress.tour.management.mapper.GuideMapper.GUIDE_MAPPER;
import static az.ingress.tour.management.mapper.TourDetailMapper.TOUR_DETAIL_MAPPER;
import static az.ingress.tour.management.mapper.TravelerMapper.TRAVELER_MAPPER;
import static az.ingress.tour.management.model.enums.TourStatus.ACTIVE;

public enum TourMapper {
    TOUR_MAPPER;

    public TourEntity fetchTourIfExist(Long id, TourRepository tourRepository) {
        return tourRepository.findById(id).orElseThrow(() ->
                new NotFoundException(TOUR_NOT_FIND.getCode(), TOUR_NOT_FIND.getMessage()));
    }

    public TourResponse buildTourResponse(TourEntity tour) {
        return TourResponse.builder()
                .id(tour.getId())
                .name(tour.getName())
                .price(tour.getPrice())
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .status(tour.getStatus())
                .detail(TOUR_DETAIL_MAPPER.buildTourDetailResponse(tour.getDetail()))
                .destinations(tour.getDestinations().stream()
                        .map(DESTINATION_MAPPER::buildDestinationResponse)
                        .collect(Collectors.toList()))
                .guides(tour.getGuides()
                        .stream()
                        .map(GUIDE_MAPPER::buildGuideResponse)
                        .collect(Collectors.toList()))
                .travelers(tour.getTravelers().stream()
                        .map(TRAVELER_MAPPER::buildTravelerResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public TourEntity buildTourEntity(TourRequest tour) {
        TourEntity tourEntity = TourEntity.builder()
                .name(tour.getName())
                .price(tour.getPrice())
                .startDate(tour.getStartDate())
                .status(ACTIVE)
                .endDate(tour.getEndDate())
                .build();

        TourDetailEntity tourDetailEntity = TourDetailEntity.builder()
                .description(tour.getTourDetail().getDescription())
                .tour(tourEntity)
                .id(tourEntity.getId())
                .build();

        tourEntity.setDetail(tourDetailEntity);

        return tourEntity;
    }
}
