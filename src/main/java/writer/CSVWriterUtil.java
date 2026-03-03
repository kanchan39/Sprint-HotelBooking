package writer;

import model.Booking;
import java.io.*;
import java.util.List;

public class CSVWriterUtil {

    // ===== WRITE CLEANED RECORDS =====
    public static void write(List<Booking> list, String path)
            throws Exception {

        BufferedWriter writer =
                new BufferedWriter(new FileWriter(path));

        writer.write("bookingId,fullName,city,stayNights\n");

        for (Booking b : list) {

            writer.write(
                    b.getBookingId() + "," +
                    b.getFullName() + "," +
                    b.getCity() + "," +
                    b.getStayNights()
            );

            writer.newLine();
        }

        writer.close();
    }

    // ===== WRITE INVALID RECORDS =====
    public static void writeInvalid(List<Booking> list, String path)
            throws Exception {

        BufferedWriter writer =
                new BufferedWriter(new FileWriter(path));

        writer.write("bookingId,fullName,email,phoneNumber\n");

        for (Booking b : list) {

            writer.write(
                    b.getBookingId() + "," +
                    b.getFullName() + "," +
                    b.getEmail() + "," +
                    b.getPhoneNumber()
            );

            writer.newLine();
        }

        writer.close();
    }
}