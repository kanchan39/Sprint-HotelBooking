package cleaning;

import model.Booking;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoomTypeNormalizer {

    private static final Logger logger =
            LogManager.getLogger(RoomTypeNormalizer.class);

    public static void normalize(List<Booking> bookings) {

        if (bookings == null || bookings.isEmpty()) {
            logger.warn("Booking list is null or empty. Skipping normalization.");
            return;
        }

        logger.info("Starting room type normalization... Total records: {}", bookings.size());

        int updatedCount = 0;
        int invalidCount = 0;

        for (Booking booking : bookings) {

            if (booking == null) {
                logger.warn("Encountered null booking object. Skipping.");
                continue;
            }

            String original = booking.getRoomType();

            // Defensive check for null/empty
            if (original == null || original.trim().isEmpty()) {
                logger.warn("Null or empty room type found.");
                booking.setRoomType(null);
                invalidCount++;
                continue;
            }

            String cleaned = original.trim().toLowerCase();

            String normalized = null;

            // Your prefix-based logic 🔥
            if (cleaned.startsWith("e")) {
                normalized = "EXECUTIVE";
            }
            else if (cleaned.startsWith("d")) {
                normalized = "DELUXE";
            }
            else if (cleaned.startsWith("st")) {
                normalized = "STANDARD";
            }
            else if (cleaned.startsWith("su")) {
                normalized = "SUITE";
            }
            else {
                logger.warn("Unknown room type: {}", original);
                invalidCount++;
            }

            booking.setRoomType(normalized);

            if (normalized != null) {
                updatedCount++;
                logger.debug("Room type normalized: {} -> {}", original, normalized);
            }
        }

        logger.info("Room type normalization completed.");
        logger.info("Total updated: {}", updatedCount);
        logger.info("Total invalid/unknown: {}", invalidCount);
    }
}
