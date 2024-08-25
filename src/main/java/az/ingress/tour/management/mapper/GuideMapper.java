package az.ingress.tour.management.mapper;

import az.ingress.tour.management.dao.entity.GuideEntity;
import az.ingress.tour.management.exception.NotFoundException;
import az.ingress.tour.management.exception.ExceptionMessage;
import az.ingress.tour.management.model.enums.GuideStatus;
import az.ingress.tour.management.model.request.GuideRequest;
import az.ingress.tour.management.dao.repository.GuideRepository;
import az.ingress.tour.management.model.response.GuideResponse;

import static az.ingress.tour.management.mapper.PassportMapper.PASSPORT_MAPPER;

public enum GuideMapper {
    GUIDE_MAPPER;

    public GuideEntity fetchGuideIfExist(Long id, GuideRepository guideRepository) {
        return guideRepository.findById(id).orElseThrow(()->
                new NotFoundException(ExceptionMessage.GUIDE_NOT_FOUND.getCode(), ExceptionMessage.GUIDE_NOT_FOUND.getMessage()));
    }

    public GuideEntity buildGuideEntity(GuideRequest request) {
        GuideEntity guideEntity = GuideEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .status(GuideStatus.ACTIVE)
                .build();

        return guideEntity;
    }

    public GuideResponse buildGuideResponse(GuideEntity guideEntity) {
        return GuideResponse.builder()
                .id(guideEntity.getId())
                .name(guideEntity.getName())
                .email(guideEntity.getEmail())
                .phoneNumber(guideEntity.getPhoneNumber())
                .status(guideEntity.getStatus())
                .passport(guideEntity.getPassport() != null ? PASSPORT_MAPPER.buildPassportResponse(guideEntity.getPassport()) : null)
                .build();
    }
}
