package com.oceanview.resort.controller.staff;

import com.oceanview.resort.service.StaffService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteStaffServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() {
        this.staffService = new StaffService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            staffService.deleteReceptionist(id);
        } catch (NumberFormatException | SQLException ignored) {
        }
        resp.sendRedirect(req.getContextPath() + "/staff/manage");
    }
}
