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

public class BillServlet extends HttpServlet {
    private ReservationService reservationService;

    @Override
    public void init() {
        this.reservationService = new ReservationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resNumberStr = req.getParameter("resNumber");
        if (resNumberStr == null || resNumberStr.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/reservations");
            return;
        }

        try {
            int num = Integer.parseInt(resNumberStr.trim());
            Reservation r = reservationService.getByNumber(num);
            if (r == null) {
                req.setAttribute("error", "Reservation not found");
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/bill.jsp");
                rd.forward(req, resp);
                return;
            }

            long nights = reservationService.calculateNights(r);
            BigDecimal total = r.getRoomPrice().multiply(BigDecimal.valueOf(nights));

            req.setAttribute("reservation", r);
            req.setAttribute("nights", nights);
            req.setAttribute("total", total);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/bill.jsp");
            rd.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid reservation number");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/bill.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("error", "Failed to generate bill");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/bill.jsp");
            rd.forward(req, resp);
        }
    }
}
