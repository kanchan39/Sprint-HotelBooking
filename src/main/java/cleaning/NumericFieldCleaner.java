package cleaning;

import model.Booking;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumericFieldCleaner {

    private static final Logger logger =
            LogManager.getLogger(NumericFieldCleaner.class);

    public static void clean(List<Booking> list) {

        logger.info("Cleaning numeric fields...");

        for (Booking b : list) {

            if (b.getRoomRate() == null || b.getRoomRate() < 0) {
                logger.warn("Invalid room rate for bookingId {}. Setting to 0.",
                        b.getBookingId());
                b.setRoomRate(0.0);
            }

            if (b.getNumGuests() == null || b.getNumGuests() <= 0) {
                logger.warn("Invalid numGuests for bookingId {}. Setting to 1.",
                        b.getBookingId());
                b.setNumGuests(1);
            }

            if (b.getTaxAmount() == null || b.getTaxAmount() < 0) {
                b.setTaxAmount(0.0);
            }

            if (b.getDiscountAmount() == null || b.getDiscountAmount() < 0) {
                b.setDiscountAmount(0.0);
            }
        }

        logger.info("Numeric cleaning completed.");
    }
}