package cleaning;

import model.Booking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class CodeMapper {

    private static final Logger logger =
            LogManager.getLogger(CodeMapper.class);

    private static final Map<String, String> channelMap =
            Map.of(
                    "OTA", "Online Travel Agency",
                    "TA", "Travel Agent",
                    "CORP", "Corporate",
                    "DIRECT", "Direct Booking"
            );

    public static void map(List<Booking> list) {

        logger.info("Mapping short codes...");

        for (Booking b : list) {

            String channel = b.getBookingChannel();

            if (channel != null &&
                channelMap.containsKey(channel.toUpperCase())) {

                b.setBookingChannel(
                        channelMap.get(channel.toUpperCase()));

                logger.debug("Mapped booking channel for bookingId {}",
                        b.getBookingId());
            }
        }

        logger.info("Code mapping completed.");
    }
}