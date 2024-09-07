package az.ingress.tour.management.service.concrete;

import az.ingress.tour.management.dao.entity.DestinationEntity;
import az.ingress.tour.management.dao.entity.TourEntity;
import az.ingress.tour.management.model.criteria.DestinationCriteria;
import az.ingress.tour.management.model.criteria.PageCriteria;
import az.ingress.tour.management.model.response.DestinationResponse;
import az.ingress.tour.management.model.response.PageableResponse;
import az.ingress.tour.management.service.specification.DestinationSpecification;
import az.ingress.tour.management.dao.repository.DestinationRepository;
import az.ingress.tour.management.model.request.DestinationRequest;
import az.ingress.tour.management.service.abstraction.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static az.ingress.tour.management.mapper.DestinationMapper.DESTINATION_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationServiceHandler implements DestinationService {
    private final DestinationRepository destinationRepository;

    @Override
    public void saveDestination(DestinationRequest destination) {
        log.info("ActionLog.saveDestination.start");
        DestinationEntity destinationEntity = DESTINATION_MAPPER.buildDestinationEntity(destination);
        destinationRepository.save(destinationEntity);
        log.info("ActionLog.saveDestination.success");
    }

    @Override
    public void saveDestinationToTour(TourEntity tour, DestinationRequest request) {
        log.info("ActionLog.saveDestination.start with id: {}", tour.getId());
        DestinationEntity destinationEntity = DESTINATION_MAPPER.buildDestinationEntity(request);
        destinationEntity.setTour(tour);
        destinationRepository.save(destinationEntity);
        log.info("ActionLog.saveDestination.success with id: {}", tour.getId());
    }

    @Override
    public PageableResponse<DestinationResponse> getDestinations(Long tourId,
                                                                 PageCriteria pageCriteria,
                                                                 DestinationCriteria destinationCriteria) {
        log.info("ActionLog.getDestinations.start with tourId: {}", tourId);
        Page<DestinationEntity> destinationPage = destinationRepository.findAll(
                new DestinationSpecification(destinationCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").ascending()));
        log.info("ActionLog.getDestinations.success with tourId: {}", tourId);

        return DESTINATION_MAPPER.mapPageableDestinationResponse(destinationPage);
    }
}
