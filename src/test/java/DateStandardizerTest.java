import cleaning.DateStandardizer;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DateStandardizerTest {

    @Test
    public void testDateStandardization() {

        Booking b = new Booking();
        b.setBookingDate(LocalDate.of(2024,1,10));

        List<Booking> list = new ArrayList<>();
        list.add(b);

        DateStandardizer.standardize(list);

        assertNotNull(b.getBookingDate());
    }
}