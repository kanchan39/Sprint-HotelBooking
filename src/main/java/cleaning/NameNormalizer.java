package cleaning;

import model.Booking;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NameNormalizer {

    private static final Logger logger =
            LogManager.getLogger(NameNormalizer.class);

    public static void normalize(List<Booking> list) {

        logger.info("Normalizing names...");

        for (Booking b : list) {

            if (b.getFullName() != null) {

                String original = b.getFullName();

                String name = original.trim().toLowerCase();
                String[] parts = name.split(" ");
                StringBuilder proper = new StringBuilder();

                for (String part : parts) {
                    if (!part.isEmpty()) {
                        proper.append(
                                Character.toUpperCase(part.charAt(0)))
                              .append(part.substring(1))
                              .append(" ");
                    }
                }

                b.setFullName(proper.toString().trim());

                logger.debug("Normalized: {} -> {}", original, b.getFullName());
            }
        }

        logger.info("Name normalization completed.");
    }
}