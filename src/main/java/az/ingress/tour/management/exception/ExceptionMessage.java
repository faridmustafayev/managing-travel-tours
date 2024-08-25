package az.ingress.tour.management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    TRAVELER_NOT_FOUND("TRAVELER_NOT_FOUND", "Traveler not found"),
    GUIDE_NOT_FOUND("GUIDE_NOT_FOUND", "Guide not found"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Http type is not correct"),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred"),
    TOUR_NOT_FIND("TOUR_NOT_FIND", "Tour not found");
    private String code;
    private String message;
}
