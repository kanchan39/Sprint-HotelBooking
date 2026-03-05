import reader.CSVReaderUtil;
import util.ETLReport;
import writer.CSVWriterUtil;

import cleaning.DuplicateRemover;
import cleaning.NameNormalizer;
import cleaning.NumericFieldCleaner;
import cleaning.DateStandardizer;
import cleaning.CodeMapper;
// import cleaning.StatusValidator; deleted 
import cleaning.ValidationResult;
import cleaning.InvalidRecordFlagger;
import cleaning.DerivedFieldCalculator;

// NEW NORMALIZERS
import cleaning.DataNormalizer;
import cleaning.EmailNormalizer;
import cleaning.PaymentMethodNormalizer;
import cleaning.PaymentStatusNormalizer;
import cleaning.RoomTypeNormalizer;
import cleaning.BookingStatusNormalizer;

import analytics.Aggregator;
import analytics.Categorizer;
import analytics.DataQualityMetrics;
import loader.DatabaseLoader;

import model.Booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Main {

        private static final Logger logger = LogManager.getLogger(Main.class);

        public static void main(String[] args) throws Exception {

                logger.info("========== ETL PROCESS STARTED ==========");

                String input = "data/hotel_etl_30_rows.csv";
                String output = "data/cleaned_bookings.csv";
                String invalidOutput = "data/invalid_records.csv";

                // ==============================
                // EXTRACT
                // ==============================

                List<Booking> bookings = CSVReaderUtil.read(input);

                logger.info("Original Size: {}", bookings.size());

                // ==============================
                // TRANSFORM / CLEANING
                // ==============================
                long start = System.currentTimeMillis();

                logger.info("Starting normalization layer...");

                // Generic text cleanup
                DataNormalizer.normalize(bookings);

                // Name normalization
                NameNormalizer.normalize(bookings);

                // Email normalization
                EmailNormalizer.normalize(bookings);

                // Payment fields normalization
                PaymentMethodNormalizer.normalize(bookings);
                PaymentStatusNormalizer.normalize(bookings);

                // Room type normalization
                RoomTypeNormalizer.normalize(bookings);

                // Booking status normalization
                BookingStatusNormalizer.normalize(bookings);

                logger.info("Normalization layer completed.");

                // ==============================
                // NUMERIC / DATE CLEANING
                // ==============================

                NumericFieldCleaner.clean(bookings);

                DateStandardizer.standardize(bookings);

                CodeMapper.map(bookings);

                // ==============================
                // DUPLICATE REMOVAL
                // ==============================

                bookings = DuplicateRemover.remove(bookings);

                // ==============================
                // DERIVED FIELDS
                // ==============================

                DerivedFieldCalculator.compute(bookings);

                logger.info("After Cleaning Size: {}", bookings.size());

                // ==============================
                // ANALYTICS
                // ==============================

                Map<String, Long> cityCount = Aggregator.countByCity(bookings);

                logger.info("City-wise Booking Count: {}", cityCount);

                bookings.forEach(b -> {

                        if (b.getStayNights() != null) {

                                String category = Categorizer.categorizeStay(
                                                b.getStayNights());

                                logger.debug(
                                                "BookingId {} Category: {}",
                                                b.getBookingId(),
                                                category);
                        }
                });

                // ==============================
                // VALIDATION
                // ==============================

                ValidationResult result = InvalidRecordFlagger.process(bookings);

                List<Booking> validBookings = result.getValid();

                List<Booking> invalidBookings = result.getInvalid();

                logger.info("Valid records: {}", validBookings.size());
                logger.info("Invalid records: {}", invalidBookings.size());

                // ==============================
                // WRITE INVALID RECORDS
                // ==============================

                CSVWriterUtil.writeInvalid(
                                invalidBookings,
                                invalidOutput);

                logger.info("Invalid records file created.");
                DataQualityMetrics.analyze(bookings);

                // ==============================
                // WRITE CLEAN DATA (Audit CSV)
                // ==============================

                CSVWriterUtil.write(
                                validBookings,
                                output);

                logger.info("Cleaned CSV file created.");

                // ==============================
                // LOAD INTO DATABASE
                // ==============================

                logger.info("Loading cleaned data into database...");

                DatabaseLoader.insertBatch(validBookings);

                logger.info("Database load completed.");
                long end = System.currentTimeMillis();

                ETLReport.printReport(
                                bookings.size(),
                                1,
                                validBookings.size(),
                                invalidBookings.size(),
                                end - start);

                logger.info("========== ETL PROCESS COMPLETED ==========");
        }
}
