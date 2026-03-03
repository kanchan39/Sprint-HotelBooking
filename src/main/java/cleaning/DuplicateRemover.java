package cleaning;

import model.Booking;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DuplicateRemover {

    private static final Logger logger =
            LogManager.getLogger(DuplicateRemover.class);

    public static List<Booking> remove(List<Booking> list) {

        logger.info("Starting duplicate removal...");

        Set<String> seen = new HashSet<>();
        List<Booking> cleaned = new ArrayList<>();

        int duplicates = 0;

        for (Booking b : list) {

            String key =
                    safe(b.getFullName()) + "|" +
                    safe(b.getEmail()) + "|" +
                    safe(b.getPhoneNumber());

            if (seen.add(key)) {
                cleaned.add(b);
            } else {
                duplicates++;
                logger.warn("Duplicate found: {}", key);
            }
        }

        logger.info("Duplicate removal completed. Duplicates found: {}", duplicates);

        return cleaned;
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }
}