package cleaning;

import model.Booking;
import java.util.List;

public class DataNormalizer {

    public static void normalize(List<Booking> list) {

        for (Booking b : list) {

            if (b.fullName != null)
                b.fullName = b.fullName.trim();

            if (b.bookingStatus != null)
                b.bookingStatus =
                        b.bookingStatus.trim().toLowerCase();
        }
    }
}