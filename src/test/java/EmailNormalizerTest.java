import cleaning.EmailNormalizer;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmailNormalizerTest {

    @Test
    public void testEmailNormalization() {

        Booking b = new Booking();
        b.setEmail("Rahul@MAIL.com");

        List<Booking> list = new ArrayList<>();
        list.add(b);

        EmailNormalizer.normalize(list);

        assertEquals("rahul@mail.com", b.getEmail());
    }

    @Test
    public void testInvalidEmail() {

        Booking b = new Booking();
        b.setEmail("invalid@mail");

        List<Booking> list = new ArrayList<>();
        list.add(b);

        EmailNormalizer.normalize(list);

        assertNull(b.getEmail());
    }
}