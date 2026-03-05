import cleaning.DuplicateRemover;
import model.Booking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class DuplicateRemoverFailTest {

    @Disabled("Intentional failing test for demonstration")
    @Test
    public void testDuplicateFailure() {

        List<Booking> list = new ArrayList<>();

        Booking b1 = new Booking();
        b1.setFullName("Rahul");
        b1.setEmail("rahul@mail.com");
        b1.setPhoneNumber("999");

        Booking b2 = new Booking();
        b2.setFullName("Rahul");
        b2.setEmail("rahul@mail.com");
        b2.setPhoneNumber("999");

        list.add(b1);
        list.add(b2);

        List<Booking> cleaned = DuplicateRemover.remove(list);

        // INTENTIONALLY WRONG EXPECTATION
        assertEquals(2, cleaned.size());
    }
}