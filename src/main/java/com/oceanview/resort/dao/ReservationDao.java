package com.oceanview.resort.dao;

import com.oceanview.resort.model.Reservation;
import com.oceanview.resort.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    public int create(Reservation r) throws SQLException {
        String sql = "INSERT INTO Reservations (guest_name, address, contact, room_type, checkin_date, checkout_date, room_price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getGuestName());
            ps.setString(2, r.getAddress());
            ps.setString(3, r.getContact());
            ps.setString(4, r.getRoomType());
            ps.setDate(5, Date.valueOf(r.getCheckinDate()));
            ps.setDate(6, Date.valueOf(r.getCheckoutDate()));
            ps.setBigDecimal(7, r.getRoomPrice());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("Failed to create reservation");
                }
                return keys.getInt(1);
            }
        }
    }

    public Reservation findByNumber(int resNumber) throws SQLException {
        String sql = "SELECT res_number, guest_name, address, contact, room_type, checkin_date, checkout_date, room_price FROM Reservations WHERE res_number = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, resNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return map(rs);
            }
        }
    }

    public List<Reservation> findAll() throws SQLException {
        String sql = "SELECT res_number, guest_name, address, contact, room_type, checkin_date, checkout_date, room_price FROM Reservations ORDER BY res_number DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public List<String> findDistinctRoomTypes() throws SQLException {
        String sql = "SELECT DISTINCT room_type FROM Reservations WHERE room_type IS NOT NULL ORDER BY room_type";
        List<String> roomTypes = new ArrayList<>();

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roomTypes.add(rs.getString("room_type"));
            }
        }
        return roomTypes;
    }

    private static Reservation map(ResultSet rs) throws SQLException {
        Reservation r = new Reservation();
        r.setReservationNumber(rs.getInt("res_number"));
        r.setGuestName(rs.getString("guest_name"));
        r.setAddress(rs.getString("address"));
        r.setContact(rs.getString("contact"));
        r.setRoomType(rs.getString("room_type"));
        Date in = rs.getDate("checkin_date");
        Date out = rs.getDate("checkout_date");
        r.setCheckinDate(in == null ? null : in.toLocalDate());
        r.setCheckoutDate(out == null ? null : out.toLocalDate());
        BigDecimal price = rs.getBigDecimal("room_price");
        r.setRoomPrice(price);
        return r;
    }
}
