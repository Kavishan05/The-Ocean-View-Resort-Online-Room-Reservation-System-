package com.oceanview.resort.controller;

import com.oceanview.resort.model.User;
import com.oceanview.resort.service.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String selectedRole = req.getParameter("role");

        try {
            User user = authService.authenticate(username, password);
            if (user == null) {
                req.setAttribute("error", "Invalid username or password");
                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                rd.forward(req, resp);
                return;
            }

            if (selectedRole != null && !selectedRole.trim().isEmpty()) {
                String sr = selectedRole.trim();
                boolean ok;
                if (sr.equalsIgnoreCase("MANAGER")) {
                    ok = user.isManager();
                } else if (sr.equalsIgnoreCase("RECEPTIONIST")) {
                    ok = user.isReceptionist();
                } else {
                    ok = false;
                }

                if (!ok) {
                    req.setAttribute("error", "Role mismatch. Please select the correct role (Admin/Staff).");
                    RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                    rd.forward(req, resp);
                    return;
                }
            }

            HttpSession session = req.getSession(true);
            session.setAttribute("authUser", user);
            session.setMaxInactiveInterval(30 * 60);

            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } catch (SQLException e) {
            getServletContext().log("Login failed due to database error", e);
            req.setAttribute("error", "Login failed: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req, resp);
        }
    }
}
