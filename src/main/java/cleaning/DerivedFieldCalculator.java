package cleaning;

import model.Booking;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DerivedFieldCalculator {

    private static final Logger logger =
            LogManager.getLogger(DerivedFieldCalculator.class);

    public static void compute(List<Booking> list) {

        logger.info("Calculating derived fields...");

        for (Booking b : list) {

            if (b.getCheckinDate() != null &&
                b.getCheckoutDate() != null) {

                long nights = ChronoUnit.DAYS.between(
                        b.getCheckinDate(),
                        b.getCheckoutDate());

                b.setStayNights(nights);

                logger.debug("BookingId {} -> Stay Nights: {}",
                        b.getBookingId(), nights);
            }
        }

        logger.info("Derived field calculation completed.");
    }
}