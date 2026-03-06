package com.oceanview.resort.controller.staff;

import com.oceanview.resort.service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddStaffServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() {
        this.staffService = new StaffService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addStaff.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            staffService.addReceptionist(name, username, password, email);
            resp.sendRedirect(req.getContextPath() + "/staff/manage");
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addStaff.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to add staff. Username/email may already exist.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addStaff.jsp");
            rd.forward(req, resp);
        }
    }
}
