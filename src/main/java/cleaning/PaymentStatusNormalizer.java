package cleaning;

import model.Booking;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentStatusNormalizer {

    private static final Logger logger =
            LogManager.getLogger(PaymentStatusNormalizer.class);

    // Map for normalization
    private static final Map<String, String> STATUS_MAP = new HashMap<>();

    // Set to store unknown statuses
    private static final Set<String> UNKNOWN_STATUSES = new HashSet<>();

    static {

        // PAID
        STATUS_MAP.put("paid", "PAID");
        STATUS_MAP.put("paidd", "PAID");
        STATUS_MAP.put("pai", "PAID");

        // PENDING
        STATUS_MAP.put("pending", "PENDING");
        STATUS_MAP.put("pend", "PENDING");
        STATUS_MAP.put("pendng", "PENDING");
        STATUS_MAP.put("pndng", "PENDING");

        // FAILED
        STATUS_MAP.put("failed", "FAILED");
        STATUS_MAP.put("faild", "FAILED");
        STATUS_MAP.put("failedd", "FAILED");
        STATUS_MAP.put("faled", "FAILED");
    }

    public static void normalize(List<Booking> bookings) {

        if (bookings == null || bookings.isEmpty()) {
            logger.warn("Booking list empty. Skipping payment normalization.");
            return;
        }

        logger.info("Starting payment status normalization... Records: {}", bookings.size());

        int normalizedCount = 0;

        for (Booking booking : bookings) {

            if (booking == null)
                continue;

            String original = booking.getPaymentStatus();

            if (original == null || original.trim().isEmpty())
                continue;

            // Clean text
            String cleaned = original
                    .toLowerCase()
                    .replaceAll("[^a-z]", "")  // remove spaces and symbols
                    .trim();

            String normalized = STATUS_MAP.get(cleaned);

            if (normalized != null) {

                booking.setPaymentStatus(normalized);
                normalizedCount++;

                logger.debug("Payment normalized: {} -> {}", original, normalized);

            } else {

                UNKNOWN_STATUSES.add(original);

                logger.warn("Unknown payment status: {}", original);
            }
        }

        logger.info("Payment normalization completed.");
        logger.info("Total normalized: {}", normalizedCount);
        logger.info("Unknown unique statuses: {}", UNKNOWN_STATUSES.size());

        // Print unknown values
        for (String s : UNKNOWN_STATUSES) {
            logger.info("Unknown status found: {}", s);
        }
    }
}
