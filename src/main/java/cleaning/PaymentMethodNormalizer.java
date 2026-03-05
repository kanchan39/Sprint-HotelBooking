package cleaning;

import model.Booking;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentMethodNormalizer {

    private static final Logger logger =
            LogManager.getLogger(PaymentMethodNormalizer.class);

    private static final Map<String, String> METHOD_MAP = new HashMap<>();

    static {

        METHOD_MAP.put("cash", "CASH");
        METHOD_MAP.put("csh", "CASH");

        METHOD_MAP.put("upi", "UPI");

        METHOD_MAP.put("creditcard", "CREDIT_CARD");
        METHOD_MAP.put("credit card", "CREDIT_CARD");
        METHOD_MAP.put("crditcard", "CREDIT_CARD");

        METHOD_MAP.put("debitcard", "DEBIT_CARD");
        METHOD_MAP.put("debit card", "DEBIT_CARD");

        METHOD_MAP.put("netbanking", "NET_BANKING");
        METHOD_MAP.put("net banking", "NET_BANKING");
        METHOD_MAP.put("netbankng", "NET_BANKING");

        METHOD_MAP.put("wallet", "WALLET");
        METHOD_MAP.put("wallt", "WALLET");
    }

    public static void normalize(List<Booking> bookings) {

        if (bookings == null || bookings.isEmpty()) {
            logger.warn("Booking list empty. Skipping normalization.");
            return;
        }

        logger.info("Starting payment method normalization... Records: {}", bookings.size());

        int normalizedCount = 0;

        for (Booking booking : bookings) {

            if (booking == null)
                continue;

            String original = booking.getPaymentMethod();

            if (original == null || original.trim().isEmpty())
                continue;

            String cleaned = original
                    .toLowerCase()
                    .replaceAll("\\s+", " ")
                    .trim();

            String key = cleaned.replaceAll(" ", "");

            String normalized = METHOD_MAP.get(cleaned);

            if (normalized == null) {
                normalized = METHOD_MAP.get(key);
            }

            if (normalized != null) {

                booking.setPaymentMethod(normalized);
                normalizedCount++;

                logger.debug("Normalized: {} -> {}", original, normalized);

            } else {

                logger.warn("Unknown payment method: {}", original);
            }
        }

        logger.info("Normalization finished.");
        logger.info("Total normalized: {}", normalizedCount);
    }
}
