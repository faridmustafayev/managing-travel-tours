package az.ingress.tour.management.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassportResponse {
    private Long id;
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String country;
}
