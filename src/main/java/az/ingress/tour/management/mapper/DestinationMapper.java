package az.ingress.tour.management.mapper;

import az.ingress.tour.management.dao.entity.DestinationEntity;
import az.ingress.tour.management.model.response.DestinationResponse;
import az.ingress.tour.management.model.response.PageableResponse;
import az.ingress.tour.management.model.request.DestinationRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public enum DestinationMapper {
    DESTINATION_MAPPER;

    public DestinationEntity buildDestinationEntity(DestinationRequest destination) {
        return DestinationEntity.builder()
                .location(destination.getLocation())
                .description(destination.getDescription())
                .visitDate(destination.getVisitDate())
                .build();
    }

    public PageableResponse<DestinationResponse> mapPageableDestinationResponse(Page<DestinationEntity> destinationPage) {
        List<DestinationEntity> destinations = destinationPage.getContent();

        List<DestinationResponse> destinationResponses = destinations.stream()
                .map(DESTINATION_MAPPER::buildDestinationResponse)
                .collect(Collectors.toList());

        return PageableResponse.<DestinationResponse>builder()
                .content(destinationResponses)
                .lastPageNumber(destinationPage.getTotalPages())
                .totalElements(destinationPage.getTotalElements())
                .hasNextPage(destinationPage.hasNext())
                .build();
    }

    public DestinationResponse buildDestinationResponse(DestinationEntity destination) {
        return DestinationResponse.builder()
                .id(destination.getId())
                .location(destination.getLocation())
                .description(destination.getDescription())
                .visitDate(destination.getVisitDate())
                .build();
    }
}
