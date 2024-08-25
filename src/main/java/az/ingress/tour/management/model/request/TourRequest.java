package az.ingress.tour.management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourRequest {
    private String name;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private TourDetailRequest tourDetail;
}
