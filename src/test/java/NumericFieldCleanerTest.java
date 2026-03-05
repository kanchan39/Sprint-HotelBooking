import cleaning.NumericFieldCleaner;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NumericFieldCleanerTest {

    @Test
    public void testNumericCleaning() {

        Booking b = new Booking();
        b.setRoomRate(-100.0);
        b.setNumGuests(0);

        List<Booking> list = new ArrayList<>();
        list.add(b);

        NumericFieldCleaner.clean(list);

        assertEquals(0.0, b.getRoomRate());
        assertEquals(1, b.getNumGuests());
    }
}