package com.oceanview.resort.controller.reservation;

import com.oceanview.resort.model.Reservation;
import com.oceanview.resort.service.ReservationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SearchReservationServlet extends HttpServlet {
    private ReservationService reservationService;

    @Override
    public void init() {
        this.reservationService = new ReservationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resNumberStr = req.getParameter("resNumber");
        try {
            req.setAttribute("reservations", reservationService.getAll());
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to load reservations");
        }

        if (resNumberStr != null && !resNumberStr.trim().isEmpty()) {
            try {
                int num = Integer.parseInt(resNumberStr.trim());
                Reservation r = reservationService.getByNumber(num);
                if (r == null) {
                    req.setAttribute("error", "Reservation not found");
                } else {
                    req.setAttribute("reservation", r);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid reservation number");
            } catch (SQLException e) {
                req.setAttribute("error", "Search failed");
            }
        }

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/viewReservation.jsp");
        rd.forward(req, resp);
    }
}
