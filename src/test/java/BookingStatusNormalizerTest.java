import cleaning.BookingStatusNormalizer;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingStatusNormalizerTest {

    @Test
    public void testStatusNormalization() {

        Booking b = new Booking();
        b.setBookingStatus("cnfrmd");

        List<Booking> list = new ArrayList<>();
        list.add(b);

        BookingStatusNormalizer.normalize(list);

        assertEquals("CONFIRMED", b.getBookingStatus());
    }
}