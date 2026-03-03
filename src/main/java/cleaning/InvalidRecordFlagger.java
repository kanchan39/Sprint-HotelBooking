package cleaning;

import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InvalidRecordFlagger {

    private static final Logger logger =
            LogManager.getLogger(InvalidRecordFlagger.class);

    public static List<Booking> filter(List<Booking> list) {

        logger.info("Filtering invalid records...");

        List<Booking> valid = new ArrayList<>();

        for (Booking b : list) {

            boolean isValid = true;

            if (b.getEmail() == null ||
                !b.getEmail().contains("@")) {

                logger.warn("Invalid email for bookingId {}",
                        b.getBookingId());
                isValid = false;
            }

            if (b.getPhoneNumber() == null ||
                b.getPhoneNumber().length() < 10) {

                logger.warn("Invalid phone for bookingId {}",
                        b.getBookingId());
                isValid = false;
            }

            if (isValid) {
                valid.add(b);
            }
        }

        logger.info("Invalid filtering completed. Valid size: {}",
                valid.size());

        return valid;
    }
}