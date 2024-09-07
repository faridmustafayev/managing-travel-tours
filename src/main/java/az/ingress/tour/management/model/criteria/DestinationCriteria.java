package az.ingress.tour.management.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationCriteria {
    private String location;
    private LocalDate visitDate;
}
