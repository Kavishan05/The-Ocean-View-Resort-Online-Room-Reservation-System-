package com.oceanview.resort.service;

import com.oceanview.resort.dao.UserDao;
import com.oceanview.resort.model.User;
import com.oceanview.resort.util.PasswordUtil;

import java.sql.SQLException;

public class AuthService {
    private final UserDao userDao;

    public AuthService() {
        this.userDao = new UserDao();
    }

    public User authenticate(String username, String password) throws SQLException {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        User user = userDao.findByUsername(username.trim());
        if (user == null) {
            return null;
        }

        String stored = user.getPasswordHash();
        boolean bcryptLike = stored != null && stored.startsWith("$2");
        if (bcryptLike) {
            if (!PasswordUtil.verify(password, stored)) {
                return null;
            }
        } else {
            if (stored == null || !stored.equals(password)) {
                return null;
            }
            String upgraded = PasswordUtil.hash(password);
            userDao.updatePasswordHash(user.getId(), upgraded);
            user.setPasswordHash(upgraded);
        }
        return user;
    }
}
