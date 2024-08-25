package az.ingress.tour.management.service.abstraction;

import az.ingress.tour.management.dao.entity.GuideEntity;
import az.ingress.tour.management.model.request.PassportRequest;
import az.ingress.tour.management.model.response.PassportResponse;

public interface PassportService {
    void savePassport(GuideEntity guideEntity, PassportRequest passport);

    PassportResponse getPassportByGuideId(Long guideId);
}
