package cleaning;

import model.Booking;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingStatusNormalizer {

    private static final Logger logger =
            LogManager.getLogger(BookingStatusNormalizer.class);

    // Dictionary for normalization
    private static final Map<String, String> STATUS_MAP = new HashMap<>();

    static {

        // CONFIRMED
        STATUS_MAP.put("confirmed", "CONFIRMED");
        STATUS_MAP.put("confirm", "CONFIRMED");
        STATUS_MAP.put("cnfrmd", "CONFIRMED");
        STATUS_MAP.put("cnfrm", "CONFIRMED");

        // CANCELLED
        STATUS_MAP.put("cancel", "CANCELLED");
        STATUS_MAP.put("cancelled", "CANCELLED");
        STATUS_MAP.put("canceled", "CANCELLED");
        STATUS_MAP.put("cncl", "CANCELLED");
        STATUS_MAP.put("cncld", "CANCELLED");

        // NO SHOW
        STATUS_MAP.put("no-show", "NO_SHOW");
        STATUS_MAP.put("no show", "NO_SHOW");
        STATUS_MAP.put("noshow", "NO_SHOW");
        STATUS_MAP.put("ns", "NO_SHOW");
        STATUS_MAP.put("n/s", "NO_SHOW");

        // PENDING
        STATUS_MAP.put("pending", "PENDING");
        STATUS_MAP.put("pend", "PENDING");
        STATUS_MAP.put("pndng", "PENDING");
        STATUS_MAP.put("pendng", "PENDING");
    }

    public static void normalize(List<Booking> bookings) {

        if (bookings == null || bookings.isEmpty()) {
            logger.warn("Booking list is null or empty.");
            return;
        }

        logger.info("Starting booking status normalization... Records: {}", bookings.size());

        int normalizedCount = 0;
        int unknownCount = 0;

        for (Booking booking : bookings) {

            if (booking == null) {
                continue;
            }

            String original = booking.getBookingStatus();

            if (original == null || original.trim().isEmpty()) {
                booking.setBookingStatus(null);
                unknownCount++;
                continue;
            }

            String cleaned = original.trim().toLowerCase();

            String normalized = STATUS_MAP.get(cleaned);

            if (normalized != null) {

                booking.setBookingStatus(normalized);
                normalizedCount++;

                logger.debug("Status normalized: {} -> {}", original, normalized);

            } else {

                logger.warn("Unknown booking status: {}", original);
                unknownCount++;
            }
        }

        logger.info("Booking status normalization completed.");
        logger.info("Total normalized: {}", normalizedCount);
        logger.info("Total unknown: {}", unknownCount);
    }
}
