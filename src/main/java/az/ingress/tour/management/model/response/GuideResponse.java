package az.ingress.tour.management.model.response;

import az.ingress.tour.management.model.enums.GuideStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuideResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private GuideStatus status;
    private PassportResponse passport;
}
