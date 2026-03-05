package analytics;

import model.Booking;
import java.util.List;

public class DataQualityMetrics {

    public static void analyze(List<Booking> bookings) {

        int invalidEmail = 0;
        int invalidPhone = 0;
        int negativeRate = 0;

        for (Booking b : bookings) {

            if (b.getEmail() == null || !b.getEmail().contains("@"))
                invalidEmail++;

            if (b.getPhoneNumber() == null || b.getPhoneNumber().length() < 10)
                invalidPhone++;

            if (b.getRoomRate() != null && b.getRoomRate() < 0)
                negativeRate++;
        }

        System.out.println("\n===== DATA QUALITY METRICS =====");
        System.out.println("Invalid Emails : " + invalidEmail);
        System.out.println("Invalid Phones : " + invalidPhone);
        System.out.println("Negative Rates : " + negativeRate);
        System.out.println("===============================\n");
    }
}