package transform;

import model.Booking;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DerivedFields {

    public static void compute(List<Booking> list) {

        for (Booking b : list) {

            if (b.checkinDate != null && b.checkoutDate != null) {
                b.stayNights =
                        ChronoUnit.DAYS.between(
                                b.checkinDate,
                                b.checkoutDate);
            }
        }
    }
}