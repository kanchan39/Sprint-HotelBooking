import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import model.Booking;
import cleaning.DuplicateRemover;
import cleaning.DuplicateResult;

class DuplicateRemoverTest {

    @Test
    void shouldRemoveDuplicates() {

        List<Booking> list = new ArrayList<>();

        Booking b1 = new Booking();
        b1.setFullName("Rahul");
        b1.setEmail("a@mail.com");
        b1.setPhoneNumber("999");

        Booking b2 = new Booking();
        b2.setFullName("Rahul");
        b2.setEmail("a@mail.com");
        b2.setPhoneNumber("999");

        list.add(b1);
        list.add(b2);

        DuplicateResult result = DuplicateRemover.remove(list);

        List<Booking> cleaned = result.getCleaned();

        assertEquals(1, cleaned.size());
        assertEquals(1, result.getDuplicateCount());
    }
}