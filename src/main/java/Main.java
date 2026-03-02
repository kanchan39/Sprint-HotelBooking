//package app;

import reader.CSVReaderUtil;
import cleaning.DuplicateRemover;
import transform.DerivedFields;
import writer.CSVWriterUtil;
import model.Booking;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        String input =
                "data/hotel_bookings_synthetic_10000.csv";

        String output =
                "data/cleaned_bookings.csv";

        List<Booking> bookings =
                CSVReaderUtil.read(input);

        System.out.println("Original Size: "
                + bookings.size());

        bookings = DuplicateRemover.clean(bookings);

        DerivedFields.compute(bookings);

        CSVWriterUtil.write(bookings, output);

        System.out.println("Cleaning Completed ✅");
    }
}