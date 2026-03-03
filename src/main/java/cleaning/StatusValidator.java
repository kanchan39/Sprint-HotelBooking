package cleaning;

import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StatusValidator {

    private static final Logger logger =
            LogManager.getLogger(StatusValidator.class);

    public static void normalize(List<Booking> list) {

        logger.info("Validating and normalizing status fields...");

        for (Booking b : list) {

            if (b.getBookingStatus() != null) {

                String status =
                        b.getBookingStatus().trim().toLowerCase();

                switch (status) {
                    case "confirmed":
                        b.setBookingStatus("Confirmed");
                        break;
                    case "cancelled":
                    case "canceled":
                        b.setBookingStatus("Cancelled");
                        break;
                    default:
                        logger.warn("Unknown booking status for bookingId {}: {}",
                                b.getBookingId(), status);
                }
            }

            if (b.getPaymentStatus() != null) {

                String payStatus =
                        b.getPaymentStatus().trim().toLowerCase();

                if (payStatus.equals("paid")) {
                    b.setPaymentStatus("Paid");
                } else if (payStatus.equals("pending")) {
                    b.setPaymentStatus("Pending");
                }
            }
        }

        logger.info("Status validation completed.");
    }
}