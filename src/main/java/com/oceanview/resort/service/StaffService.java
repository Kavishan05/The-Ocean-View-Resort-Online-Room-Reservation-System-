package com.oceanview.resort.service;

import com.oceanview.resort.dao.StaffDao;
import com.oceanview.resort.model.User;
import com.oceanview.resort.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

public class StaffService {
    private final StaffDao staffDao;

    public StaffService() {
        this.staffDao = new StaffDao();
    }

    public int addReceptionist(String name, String username, String password, String email) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        User u = new User();
        u.setName(name.trim());
        u.setUsername(username.trim());
        u.setEmail(email.trim());
        u.setPasswordHash(PasswordUtil.hash(password));
        u.setRole("RECEPTIONIST");

        return staffDao.createReceptionist(u);
    }

    public List<User> getAllReceptionists() throws SQLException {
        return staffDao.findAllReceptionists();
    }

    public User getReceptionist(int id) throws SQLException {
        return staffDao.findById(id);
    }

    public void updateReceptionist(int id, String name, String username, String email) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid staff id");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        User u = new User();
        u.setId(id);
        u.setName(name.trim());
        u.setUsername(username.trim());
        u.setEmail(email.trim());
        staffDao.updateReceptionist(u);
    }

    public void deleteReceptionist(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid staff id");
        }
        staffDao.deleteReceptionist(id);
    }
}
