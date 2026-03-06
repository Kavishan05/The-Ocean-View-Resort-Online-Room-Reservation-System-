package com.oceanview.resort.service;

import com.oceanview.resort.dao.ReservationDao;
import com.oceanview.resort.model.Reservation;

import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservationService {
    private final ReservationDao reservationDao;

    public ReservationService() {
        this.reservationDao = new ReservationDao();
    }

    public int addReservation(Reservation r) throws SQLException {
        validateReservation(r);
        return reservationDao.create(r);
    }

    public Reservation getByNumber(int number) throws SQLException {
        return reservationDao.findByNumber(number);
    }

    public List<Reservation> getAll() throws SQLException {
        return reservationDao.findAll();
    }

    public long calculateNights(Reservation r) {
        if (r == null || r.getCheckinDate() == null || r.getCheckoutDate() == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(r.getCheckinDate(), r.getCheckoutDate());
    }

    public List<String> getDistinctRoomTypes() throws SQLException {
        return reservationDao.findDistinctRoomTypes();
    }

    private void validateReservation(Reservation r) {
        if (r == null) {
            throw new IllegalArgumentException("Reservation is required");
        }
        if (r.getGuestName() == null || r.getGuestName().trim().isEmpty()) {
            throw new IllegalArgumentException("Guest name is required");
        }
        if (r.getAddress() == null || r.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Address is required");
        }
        if (r.getContact() == null || r.getContact().trim().isEmpty()) {
            throw new IllegalArgumentException("Contact is required");
        }
        if (r.getRoomType() == null || r.getRoomType().trim().isEmpty()) {
            throw new IllegalArgumentException("Room type is required");
        }
        if (r.getCheckinDate() == null || r.getCheckoutDate() == null) {
            throw new IllegalArgumentException("Check-in and check-out dates are required");
        }
        if (!r.getCheckoutDate().isAfter(r.getCheckinDate())) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        if (r.getRoomPrice() == null || r.getRoomPrice().signum() <= 0) {
            throw new IllegalArgumentException("Room price must be greater than 0");
        }
    }
}
