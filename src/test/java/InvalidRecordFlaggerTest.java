import cleaning.InvalidRecordFlagger;
import cleaning.ValidationResult;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidRecordFlaggerTest {

    @Test
    public void testValidation() {

        Booking b = new Booking();
        b.setEmail(null);

        List<Booking> list = new ArrayList<>();
        list.add(b);

        ValidationResult result = InvalidRecordFlagger.process(list);

        assertEquals(1, result.getInvalid().size());
    }
}