package az.ingress.tour.management.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldNameConstants
@NoArgsConstructor
@Entity
@Table(name = "destinations")
public class DestinationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String location;

    private String description;

    private LocalDate visitDate;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @ToString.Exclude
    private TourEntity tour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationEntity that = (DestinationEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
