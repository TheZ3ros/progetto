package com.example.progetto.controller_app;

import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.bean.TripStatusBean;
import com.example.progetto.dao.TripStatusDAO;
import com.example.progetto.model.TripStatus;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetTripStatusController {

    public static List<TripStatusBean> showtripstatus(int id) throws SQLException, IOException, NotValidCouponException {
        TripStatusDAO statusDAO = new TripStatusDAO();
        List <TripStatusBean> statusBeans = new ArrayList<>();

        List<TripStatus> statuses = statusDAO.execute(id);
        for (TripStatus status : statuses) {
            TripStatusBean tripstatus = new TripStatusBean(status.getUsername(), status.isStatus());
            statusBeans.add(tripstatus);
        }

        return statusBeans;
    }
}
