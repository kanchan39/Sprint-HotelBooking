package cleaning;

import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DataNormalizer {

    private static final Logger logger =
            LogManager.getLogger(DataNormalizer.class);

    public static void normalize(List<Booking> list) {

        logger.info("Starting general data normalization...");

        for (Booking b : list) {

            b.setFullName(trim(b.getFullName()));
            b.setEmail(trimLower(b.getEmail()));
            b.setPhoneNumber(trim(b.getPhoneNumber()));
            b.setCity(trim(b.getCity()));
            b.setState(trim(b.getState()));
            b.setCountry(trimUpper(b.getCountry()));
            b.setBookingChannel(trim(b.getBookingChannel()));
            b.setBookingStatus(trim(b.getBookingStatus()));
            b.setPaymentMethod(trimUpper(b.getPaymentMethod()));
            b.setPaymentStatus(trim(b.getPaymentStatus()));
        }

        logger.info("Data normalization completed.");
    }

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }

    private static String trimLower(String s) {
        return s == null ? null : s.trim().toLowerCase();
    }

    private static String trimUpper(String s) {
        return s == null ? null : s.trim().toUpperCase();
    }
}