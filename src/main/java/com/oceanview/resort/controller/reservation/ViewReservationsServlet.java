package com.oceanview.resort.controller.reservation;

import com.oceanview.resort.service.ReservationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ViewReservationsServlet extends HttpServlet {
    private ReservationService reservationService;

    @Override
    public void init() {
        this.reservationService = new ReservationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("reservations", reservationService.getAll());
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to load reservations.");
        }

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/viewReservation.jsp");
        rd.forward(req, resp);
    }
}
