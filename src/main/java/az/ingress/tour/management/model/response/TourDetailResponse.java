package az.ingress.tour.management.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TourDetailResponse {
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
