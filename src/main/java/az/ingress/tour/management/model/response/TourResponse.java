package az.ingress.tour.management.model.response;

import az.ingress.tour.management.model.enums.TourStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private TourStatus status;
    private TourDetailResponse detail;
    private List<DestinationResponse> destinations = new ArrayList<>();
    private List<GuideResponse> guides = new ArrayList<>();
    private List<TravelerResponse> travelers = new ArrayList<>();
}
