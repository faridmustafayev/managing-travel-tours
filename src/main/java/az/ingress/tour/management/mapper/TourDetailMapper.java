package az.ingress.tour.management.mapper;

import az.ingress.tour.management.dao.entity.TourDetailEntity;
import az.ingress.tour.management.model.response.TourDetailResponse;

public enum TourDetailMapper {
    TOUR_DETAIL_MAPPER;

    public TourDetailResponse buildTourDetailResponse(TourDetailEntity tourDetailEntity) {
        return TourDetailResponse.builder()
                .id(tourDetailEntity.getId())
                .description(tourDetailEntity.getDescription())
                .createdAt(tourDetailEntity.getCreatedAt())
                .updatedAt(tourDetailEntity.getUpdatedAt())
                .build();
    }
}
