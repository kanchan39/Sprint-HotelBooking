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

                b.setBookingId(idCounter++);

                b.setHotelId(parseInt(row[1]));
                b.setCustomerId(parseInt(row[2]));

                b.setFullName(row[3]);
                b.setEmail(row[4]);
                b.setPhoneNumber(row[5]);

                b.setBookingDate(parseDate(row[6]));
                b.setCheckinDate(parseDate(row[7]));
                b.setCheckoutDate(parseDate(row[8]));

                b.setRoomType(row[9]);
                b.setRoomRate(parseDouble(row[10]));
                b.setNumGuests(parseInt(row[11]));

                b.setBookingChannel(row[12]);
                b.setBookingStatus(row[13]);

                b.setPaymentStatus(row[14]);
                b.setPaymentMethod(row[15]);

                b.setTaxAmount(parseDouble(row[16]));
                b.setDiscountAmount(parseDouble(row[17]));

                b.setCity(row[18]);
                b.setState(row[19]);
                b.setCountry(row[20]);

                bookings.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }

    private static Integer parseInt(String v) {
        try {
            return Integer.parseInt(v.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private static Double parseDouble(String v) {
        try {
            return Double.parseDouble(v.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private static LocalDate parseDate(String v) {
        try {
            return LocalDate.parse(v);
        } catch (Exception e) {
            return null;
        }
    }
}