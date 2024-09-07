package az.ingress.tour.management.dao.entity;

import az.ingress.tour.management.model.enums.TourStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tours")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(STRING)
    private TourStatus status;

    @OneToOne(mappedBy = "tour", fetch = LAZY, cascade = ALL)
    @ToString.Exclude
    private TourDetailEntity detail;

    @OneToMany(mappedBy = "tour", cascade = ALL)
    @ToString.Exclude
    private List<DestinationEntity> destinations;

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "tours_guides",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "guide_id")
    )
    @ToString.Exclude
    private List<GuideEntity> guides;

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "tours_travelers",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "traveler_id")
    )
    @ToString.Exclude
    private List<TravelerEntity> travelers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourEntity that = (TourEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
