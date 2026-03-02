package reader;

import com.opencsv.CSVReader;
import model.Booking;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

public class CSVReaderUtil {

    public static List<Booking> read(String path) {

        List<Booking> bookings = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {

            String[] row;
            reader.readNext(); // skip header

            int idCounter = 1;

            while ((row = reader.readNext()) != null) {

                Booking b = new Booking();

                // Generate ID safely
                b.bookingId = idCounter++;

                b.hotelId = parseInt(row[1]);
                b.customerId = parseInt(row[2]);

                b.fullName = row[3];
                b.email = row[4];
                b.phoneNumber = row[5];

                b.bookingDate = parseDate(row[6]);
                b.checkinDate = parseDate(row[7]);
                b.checkoutDate = parseDate(row[8]);

                b.roomType = row[9];
                b.roomRate = parseDouble(row[10]);
                b.numGuests = parseInt(row[11]);

                b.bookingChannel = row[12];
                b.bookingStatus = row[13];

                b.paymentStatus = row[14];
                b.paymentMethod = row[15];

                b.taxAmount = parseDouble(row[16]);
                b.discountAmount = parseDouble(row[17]);

                b.city = row[18];
                b.state = row[19];
                b.country = row[20];

                bookings.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }

    private static Integer parseInt(String v) {
        try { return Integer.parseInt(v.trim()); }
        catch (Exception e) { return null; }
    }

    private static Double parseDouble(String v) {
        try { return Double.parseDouble(v.trim()); }
        catch (Exception e) { return null; }
    }

    private static LocalDate parseDate(String v) {
        try { return LocalDate.parse(v); }
        catch (Exception e) { return null; }
    }
}