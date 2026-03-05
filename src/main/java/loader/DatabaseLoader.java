package loader;

import model.Booking;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class DatabaseLoader {

    public static void insertBatch(List<Booking> list) {

        String sql = "INSERT INTO booking_cleaned VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnectionUtil.getConnection()) {

            con.setAutoCommit(false); // 🔥 Start transaction

            PreparedStatement ps = con.prepareStatement(sql);
      

            for (Booking b : list) {

                ps.setInt(1, b.getBookingId());
                ps.setObject(2, b.getHotelId());
                ps.setObject(3, b.getCustomerId());

                ps.setString(4, b.getFullName());
                ps.setString(5, b.getEmail());
                ps.setString(6, b.getPhoneNumber());

                ps.setObject(7, b.getBookingDate());
                ps.setObject(8, b.getCheckinDate());
                ps.setObject(9, b.getCheckoutDate());

                ps.setString(10, b.getRoomType());
                ps.setObject(11, b.getRoomRate());
                ps.setObject(12, b.getNumGuests());

                ps.setString(13, b.getBookingChannel());
                ps.setString(14, b.getBookingStatus());

                ps.setString(15, b.getPaymentStatus());
                ps.setString(16, b.getPaymentMethod());

                ps.setObject(17, b.getTaxAmount());
                ps.setObject(18, b.getDiscountAmount());

                ps.setString(19, b.getCity());
                ps.setString(20, b.getState());
                ps.setString(21, b.getCountry());

                ps.setObject(22, b.getStayNights());

                ps.addBatch();
            }

            ps.executeBatch(); // 🔥 Batch insert

            con.commit(); // 🔥 Commit if success

            System.out.println("Batch Insert Successful!");

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error occurred. Rolling back transaction...");
        }
    }
}
