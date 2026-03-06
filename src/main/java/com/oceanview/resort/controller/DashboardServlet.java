package com.oceanview.resort.controller;

import com.oceanview.resort.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("authUser");

        req.setAttribute("displayName", user.getName());
        req.setAttribute("displayUsername", user.getUsername());

        String view;
        if (user.isManager()) {
            view = "/WEB-INF/views/managerDashboard.jsp";
        } else {
            view = "/WEB-INF/views/receptionistDashboard.jsp";
        }

        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req, resp);
    }
}
