package transform;

import model.Booking;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DerivedFields {

    public static void compute(List<Booking> list) {

        for (Booking b : list) {

            if (b.getCheckinDate() != null &&
                b.getCheckoutDate() != null) {

                long nights =
                        ChronoUnit.DAYS.between(
                                b.getCheckinDate(),
                                b.getCheckoutDate());

                b.setStayNights(nights);
            }
        }
    }
}