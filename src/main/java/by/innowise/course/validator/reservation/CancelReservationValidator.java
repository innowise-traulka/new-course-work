package by.innowise.course.validator.reservation;

import by.innowise.course.entities.Reservation;
import by.innowise.course.validator.AbstractValidator;
import by.innowise.course.validator.type.ValidatorType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CancelReservationValidator extends AbstractValidator<Reservation> {

    @Override
    public void validate(Reservation reservation) {
        errorList.clear(); // TODO: 04/03/2021 ??/
        if (reservation.getArrivalDate().isBefore(LocalDate.now())) {
            addError("Application date is before date now");
        }
        throwException("Validate reservation exception", getErrorList());
    }

    @Override
    public ValidatorType name() {
        return ValidatorType.CANCEL_RESERVATION_VALIDATOR;
    }
}
