package cleaning;

import model.Booking;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailNormalizer {

    private static final Logger logger =
            LogManager.getLogger(EmailNormalizer.class);

    // Simple email regex
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static void normalize(List<Booking> list) {

        logger.info("Starting email normalization...");

        for (Booking b : list) {

            String original = b.getEmail();

            // Defensive check 1
            if (original == null || original.trim().isEmpty() ||
                original.equalsIgnoreCase("NA")) {

                logger.warn("Invalid email (null/NA): {}", original);
                b.setEmail(null);
                continue;
            }

            String email = original.trim().toLowerCase();

            // Remove double dots
            email = email.replaceAll("\\.\\.+", ".");

            // Defensive check 2 - format validation
            if (!EMAIL_PATTERN.matcher(email).matches()) {

                logger.warn("Invalid email format: {}", email);
                b.setEmail(null);
                continue;
            }

            b.setEmail(email);

            logger.debug("Normalized email: {} -> {}", original, email);
        }

        logger.info("Email normalization completed.");
    }
}
