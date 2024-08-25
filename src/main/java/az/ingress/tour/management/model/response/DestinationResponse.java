package az.ingress.tour.management.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DestinationResponse {
    private Long id;
    private String location;
    private String description;
    private LocalDate visitDate;
}
