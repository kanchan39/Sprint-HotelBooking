package cleaning;

import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InvalidRecordFlagger {

    private static final Logger logger =
            LogManager.getLogger(InvalidRecordFlagger.class);

    public static ValidationResult process(List<Booking> list) {

        logger.info("Starting validation and invalid record separation...");

        List<Booking> valid = new ArrayList<>();
        List<Booking> invalid = new ArrayList<>();

        for (Booking b : list) {

            boolean isValid = true;

            // Email validation
            if (b.getEmail() == null ||
                !b.getEmail().contains("@")) {

                logger.warn("Invalid email for bookingId {}",
                        b.getBookingId());
                isValid = false;
            }

            // Phone validation
            if (b.getPhoneNumber() == null ||
                b.getPhoneNumber().length() < 10) {

                logger.warn("Invalid phone for bookingId {}",
                        b.getBookingId());
                isValid = false;
            }

            if (isValid) {
                valid.add(b);
            } else {
                invalid.add(b);
            }
        }

        logger.info("Validation completed. Valid: {}, Invalid: {}",
                valid.size(), invalid.size());

        return new ValidationResult(valid, invalid);
    }
}