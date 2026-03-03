package cleaning;

import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateStandardizer {

    private static final Logger logger =
            LogManager.getLogger(DateStandardizer.class);

    private static final DateTimeFormatter[] formats = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy")
    };

    public static void standardize(List<Booking> list) {

        logger.info("Standardizing date formats...");

        for (Booking b : list) {

            b.setBookingDate(parse(b.getBookingDate()));
            b.setCheckinDate(parse(b.getCheckinDate()));
            b.setCheckoutDate(parse(b.getCheckoutDate()));
        }

        logger.info("Date standardization completed.");
    }

    private static LocalDate parse(LocalDate date) {
        return date; // already parsed in CSVReaderUtil
    }
}