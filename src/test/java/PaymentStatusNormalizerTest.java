import cleaning.PaymentStatusNormalizer;
import model.Booking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentStatusNormalizerTest {

    @Test
    public void testPaymentStatus() {

        Booking b = new Booking();
        b.setPaymentStatus("paid");

        List<Booking> list = new ArrayList<>();
        list.add(b);

        PaymentStatusNormalizer.normalize(list);

        assertEquals("PAID", b.getPaymentStatus());
    }
}