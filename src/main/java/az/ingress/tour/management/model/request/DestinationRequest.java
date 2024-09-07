package az.ingress.tour.management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {
    private String location;
    private String description;
    private LocalDate visitDate;
}
