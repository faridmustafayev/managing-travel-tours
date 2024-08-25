package az.ingress.tour.management.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TravelerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
