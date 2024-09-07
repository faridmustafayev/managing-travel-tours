package az.ingress.tour.management.service.concrete;

import az.ingress.tour.management.dao.entity.GuideEntity;
import az.ingress.tour.management.dao.entity.PassportEntity;
import az.ingress.tour.management.dao.repository.GuideRepository;
import az.ingress.tour.management.dao.repository.PassportRepository;
import az.ingress.tour.management.model.request.PassportRequest;
import az.ingress.tour.management.model.response.PassportResponse;
import az.ingress.tour.management.service.abstraction.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static az.ingress.tour.management.mapper.GuideMapper.GUIDE_MAPPER;
import static az.ingress.tour.management.mapper.PassportMapper.PASSPORT_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceHandler implements PassportService {
    private final PassportRepository passportRepository;
    private final GuideRepository guideRepository;

    @Override
    public void savePassport(GuideEntity guideEntity, PassportRequest passport) {
        log.info("ActionLog.savePassport.start with id: {}", guideEntity.getId());
        PassportEntity passportEntity = PASSPORT_MAPPER.buildPassportEntity(passport);
        GuideEntity guide = GUIDE_MAPPER.fetchGuideIfExist(guideEntity.getId(), guideRepository);
        passportEntity.setGuide(guide);
        passportRepository.save(passportEntity);
        log.info("ActionLog.savePassport.success with id: {}", guideEntity.getId());
    }

    public PassportResponse getPassportByGuideId(Long guideId){
        log.info("ActionLog.getPassportSpecificGuide.start with guideId: {}", guideId);
        GuideEntity guide = GUIDE_MAPPER.fetchGuideIfExist(guideId, guideRepository);
        log.info("ActionLog.getPassportSpecificGuide.success with guideId: {}", guideId);
        return PASSPORT_MAPPER.buildPassportResponse(guide.getPassport());
    }
}
