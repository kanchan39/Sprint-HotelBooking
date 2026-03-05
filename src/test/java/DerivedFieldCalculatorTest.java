import cleaning.DerivedFieldCalculator;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DerivedFieldCalculatorTest {

    @Test
    public void testStayNightsCalculation() {

        Booking b = new Booking();

        b.setCheckinDate(LocalDate.of(2024,1,10));
        b.setCheckoutDate(LocalDate.of(2024,1,12));

        List<Booking> list = new ArrayList<>();
        list.add(b);

        DerivedFieldCalculator.compute(list);

        assertEquals(2, b.getStayNights());
    }
}