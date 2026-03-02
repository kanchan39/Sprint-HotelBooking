package cleaning;

import model.Booking;
import java.util.*;

public class DuplicateRemover {

    public static List<Booking> clean(List<Booking> list) {

        Set<String> seen = new HashSet<>();
        List<Booking> cleaned = new ArrayList<>();

        int duplicates = 0;

        for (Booking b : list) {

            String key =
                    safe(b.fullName) + "|" +
                    safe(b.email) + "|" +
                    safe(b.phoneNumber);

            if (seen.add(key)) {
                cleaned.add(b);
            } else {
                duplicates++;
            }
        }

        System.out.println("Duplicates Found: " + duplicates);

        return cleaned;
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }
}