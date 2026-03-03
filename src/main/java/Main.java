import reader.CSVReaderUtil;
import writer.CSVWriterUtil;

import cleaning.DuplicateRemover;
import cleaning.NameNormalizer;
import cleaning.NumericFieldCleaner;
import cleaning.DateStandardizer;
import cleaning.CodeMapper;
import cleaning.StatusValidator;
import cleaning.InvalidRecordFlagger;
import cleaning.DerivedFieldCalculator;

import analytics.Aggregator;
import analytics.Categorizer;

import model.Booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger logger =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        logger.info("========== ETL PROCESS STARTED ==========");

        String input =
                "data/hotel_bookings_synthetic_10000.csv";

        String output =
                "data/cleaned_bookings.csv";

        // ===== EXTRACT =====
        List<Booking> bookings =
                CSVReaderUtil.read(input);

        logger.info("Original Size: {}", bookings.size());

        // ===== TRANSFORM =====

        NameNormalizer.normalize(bookings);

        NumericFieldCleaner.clean(bookings);

        DateStandardizer.standardize(bookings);

        CodeMapper.map(bookings);

        StatusValidator.normalize(bookings);

        bookings = DuplicateRemover.remove(bookings);

        bookings = InvalidRecordFlagger.filter(bookings);

        DerivedFieldCalculator.compute(bookings);

        logger.info("After Cleaning Size: {}", bookings.size());

        // ===== ANALYTICS =====
        // Map<String, Long> cityCount =
        //         Aggregator.countByCity(bookings);

        // logger.info("City-wise Booking Count: {}", cityCount);

        // Categorization Example
        // bookings.forEach(b -> {
        //     if (b.getStayNights() != null) {
        //         String category =
        //                 Categorizer.categorizeStay(
        //                         b.getStayNights());
        //         logger.debug("BookingId {} Category: {}",
        //                 b.getBookingId(), category);
        //     }
        // });

        // ===== LOAD (Currently CSV) =====
        CSVWriterUtil.write(bookings, output);

        logger.info("========== ETL PROCESS COMPLETED ==========");
    }
}