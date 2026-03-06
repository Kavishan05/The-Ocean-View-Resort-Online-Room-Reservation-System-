package com.oceanview.resort.controller.staff;

import com.oceanview.resort.model.User;
import com.oceanview.resort.service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditStaffServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() {
        this.staffService = new StaffService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/staff/manage");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            User staff = staffService.getReceptionist(id);
            if (staff == null) {
                resp.sendRedirect(req.getContextPath() + "/staff/manage");
                return;
            }
            req.setAttribute("staff", staff);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editStaff.jsp");
            rd.forward(req, resp);
        } catch (NumberFormatException | SQLException e) {
            resp.sendRedirect(req.getContextPath() + "/staff/manage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        try {
            int id = Integer.parseInt(idStr);
            staffService.updateReceptionist(id, name, username, email);
            resp.sendRedirect(req.getContextPath() + "/staff/manage");
        } catch (Exception e) {
            req.setAttribute("error", "Failed to update staff");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editStaff.jsp");
            rd.forward(req, resp);
        }
    }
}
