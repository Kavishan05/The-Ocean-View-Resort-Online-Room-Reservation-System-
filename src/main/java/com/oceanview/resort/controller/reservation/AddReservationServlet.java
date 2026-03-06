package com.oceanview.resort.controller.reservation;

import com.oceanview.resort.model.Reservation;
import com.oceanview.resort.service.ReservationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AddReservationServlet extends HttpServlet {
    private ReservationService reservationService;

    @Override
    public void init() {
        this.reservationService = new ReservationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> roomTypes = reservationService.getDistinctRoomTypes();
            req.setAttribute("roomTypes", roomTypes);
        } catch (SQLException e) {
        }
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addReservation.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Reservation r = new Reservation();
        r.setGuestName(req.getParameter("guestName"));
        r.setAddress(req.getParameter("address"));
        r.setContact(req.getParameter("contact"));
        r.setRoomType(req.getParameter("roomType"));

        try {
            r.setCheckinDate(LocalDate.parse(req.getParameter("checkinDate")));
            r.setCheckoutDate(LocalDate.parse(req.getParameter("checkoutDate")));
            r.setRoomPrice(new BigDecimal(req.getParameter("roomPrice")));

            int resNo = reservationService.addReservation(r);
            resp.sendRedirect(req.getContextPath() + "/reservations/search?resNumber=" + resNo);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("reservation", r);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addReservation.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to save reservation. Please try again.");
            req.setAttribute("reservation", r);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addReservation.jsp");
            rd.forward(req, resp);
        }
    }
}
