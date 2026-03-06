package com.oceanview.resort.dao;

import com.oceanview.resort.model.User;
import com.oceanview.resort.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {

    public int createReceptionist(User staff) throws SQLException {
        String insertUser = "INSERT INTO Users (name, username, password, email, role) VALUES (?, ?, ?, ?, 'RECEPTIONIST')";
        String insertStaff = "INSERT INTO Staff (id) VALUES (?)";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement psUser = con.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                psUser.setString(1, staff.getName());
                psUser.setString(2, staff.getUsername());
                psUser.setString(3, staff.getPasswordHash());
                psUser.setString(4, staff.getEmail());
                psUser.executeUpdate();

                int userId;
                try (ResultSet keys = psUser.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("Failed to create staff user");
                    }
                    userId = keys.getInt(1);
                }

                try (PreparedStatement psStaff = con.prepareStatement(insertStaff)) {
                    psStaff.setInt(1, userId);
                    psStaff.executeUpdate();
                }

                con.commit();
                return userId;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public List<User> findAllReceptionists() throws SQLException {
        String sql = "SELECT id, name, username, password, email, role FROM Users WHERE role = 'RECEPTIONIST' ORDER BY id DESC";
        List<User> list = new ArrayList<>();

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }
        }
        return list;
    }

    public User findById(int id) throws SQLException {
        String sql = "SELECT id, name, username, password, email, role FROM Users WHERE id = ? AND role = 'RECEPTIONIST'";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                return u;
            }
        }
    }

    public void updateReceptionist(User staff) throws SQLException {
        String sql = "UPDATE Users SET name = ?, username = ?, email = ? WHERE id = ? AND role = 'RECEPTIONIST'";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, staff.getName());
            ps.setString(2, staff.getUsername());
            ps.setString(3, staff.getEmail());
            ps.setInt(4, staff.getId());
            ps.executeUpdate();
        }
    }

    public void deleteReceptionist(int id) throws SQLException {
        String sql = "DELETE FROM Users WHERE id = ? AND role = 'RECEPTIONIST'";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
