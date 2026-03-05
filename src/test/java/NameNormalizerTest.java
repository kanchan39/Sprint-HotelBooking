import cleaning.NameNormalizer;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NameNormalizerTest {

    @Test
    public void testNameNormalization() {

        Booking b = new Booking();
        b.setFullName("   rahul sharma  ");

        List<Booking> list = new ArrayList<>();
        list.add(b);

        NameNormalizer.normalize(list);

        assertEquals("Rahul Sharma", b.getFullName());
    }
}