package az.ingress.tour.management.mapper;

import az.ingress.tour.management.dao.entity.PassportEntity;
import az.ingress.tour.management.model.request.PassportRequest;
import az.ingress.tour.management.model.response.PassportResponse;

public enum PassportMapper {
    PASSPORT_MAPPER;

    public PassportEntity buildPassportEntity(PassportRequest passport) {
        return PassportEntity.builder()
                .passportNumber(passport.getPassportNumber())
                .issueDate(passport.getIssueDate())
                .expiryDate(passport.getExpiryDate())
                .country(passport.getCountry())
                .build();
    }

    public PassportResponse buildPassportResponse(PassportEntity passportEntity) {
        return PassportResponse.builder()
                .id(passportEntity.getId())
                .passportNumber(passportEntity.getPassportNumber())
                .issueDate(passportEntity.getIssueDate())
                .expiryDate(passportEntity.getExpiryDate())
                .country(passportEntity.getCountry())
                .build();
    }
}
