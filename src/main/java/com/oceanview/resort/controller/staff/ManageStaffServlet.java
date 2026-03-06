package com.oceanview.resort.controller.staff;

import com.oceanview.resort.service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ManageStaffServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() {
        this.staffService = new StaffService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("staffList", staffService.getAllReceptionists());
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to load staff");
        }

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/manageStaff.jsp");
        rd.forward(req, resp);
    }
}
