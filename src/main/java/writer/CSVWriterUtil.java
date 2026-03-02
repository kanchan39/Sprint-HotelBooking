package writer;

import model.Booking;
import java.io.*;
import java.util.List;

public class CSVWriterUtil {

    public static void write(List<Booking> list, String path)
            throws Exception {

        BufferedWriter writer =
                new BufferedWriter(new FileWriter(path));

        writer.write("bookingId,fullName,city,stayNights\n");

        for (Booking b : list) {
            writer.write(
                    b.bookingId + "," +
                    b.fullName + "," +
                    b.city + "," +
                    b.stayNights
            );
            writer.newLine();
        }

        writer.close();
    }
}