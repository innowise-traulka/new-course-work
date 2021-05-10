package by.innowise.course.validator.reservation;

import by.innowise.course.entities.Reservation;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;
import by.innowise.course.services.RoomService;
import by.innowise.course.validator.AbstractValidator;
import by.innowise.course.validator.type.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddReservationValidator extends AbstractValidator<Reservation> {
    private final HotelService hotelService;
    private final CategoryService categoryService;
    private final RoomService roomService;

    @Autowired
    public AddReservationValidator(HotelService hotelService,
                                   CategoryService categoryService,
                                   RoomService roomService) {
        this.hotelService = hotelService;
        this.categoryService = categoryService;
        this.roomService = roomService;
    }

    @Override
    public void validate(Reservation reservation) {
        errorList.clear();
        if (reservation.getArrivalDate().isAfter(reservation.getDepartureDate())) {
            errorList.add("Arrival date is after departure date");
        }
        throwException("Add reservation exception", errorList);
    }

    @Override
    public ValidatorType name() {
        return ValidatorType.ADD_RESERVATION_VALIDATOR;
    }
}
