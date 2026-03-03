package analytics;

import model.Booking;
import java.util.*;
import java.util.stream.Collectors;

public class Aggregator {

    public static Map<String, Long> countByCity(List<Booking> list) {

        return list.stream()
                .collect(Collectors.groupingBy(
                        Booking::getCity,
                        Collectors.counting()
                ));
    }
}