package az.ingress.tour.management.mapper;

import az.ingress.tour.management.dao.entity.TravelerEntity;
import az.ingress.tour.management.exception.NotFoundException;
import az.ingress.tour.management.dao.repository.TravelerRepository;
import az.ingress.tour.management.model.request.TravelerRequest;
import az.ingress.tour.management.model.response.TravelerResponse;

import static az.ingress.tour.management.exception.ExceptionMessage.TRAVELER_NOT_FOUND;

public enum TravelerMapper {
    TRAVELER_MAPPER;

    public TravelerEntity fetchTravelerIfExist(Long travelerId, TravelerRepository travelerRepository) {
        return travelerRepository.findById(travelerId).orElseThrow(()->
                new NotFoundException(TRAVELER_NOT_FOUND.getCode(), TRAVELER_NOT_FOUND.getMessage()));
    }

    public TravelerEntity buildTravelerEntity(TravelerRequest traveler) {
        return TravelerEntity.builder()
                .firstName(traveler.getFirstName())
                .lastName(traveler.getLastName())
                .email(traveler.getEmail())
                .build();
    }

    public TravelerResponse buildTravelerResponse(TravelerEntity traveler) {
        return TravelerResponse.builder()
                .id(traveler.getId())
                .firstName(traveler.getFirstName())
                .lastName(traveler.getLastName())
                .email(traveler.getEmail())
                .build();
    }
}
