package com.example.progetto.controller_app;

import com.example.progetto.bean.SearchBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.dao.csv_dbms.BookingDAOcsv;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.FailedSearchException;
import com.example.progetto.exception.PlacesTerminatedException;
import com.example.progetto.bean.BookBean;
import com.example.progetto.dao.TripDAO;
import com.example.progetto.dao.UserDAO;
import com.example.progetto.dao.csv_dbms.BookingDAOdbms;
import com.example.progetto.bean.TripBean;
import com.example.progetto.model.Trip;
import com.example.progetto.model.UserTrip;
import com.example.progetto.pattern.decorator.UserTripStatus;
import com.example.progetto.pattern.factory.EntityFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTripController {
    private static final  String WRITER ="dbms";
    public List<TripBean> showTrip() throws SQLException, IOException {
        TripDAO tripdao = new TripDAO();
        Trip trip;
        List<TripBean> viaggi = new ArrayList<>();
        int i = 1;
        while ((trip = tripdao.execute(i)) != null) {
            i++;

            TripBean tripBean = new TripBean(trip.getAvailable(), trip.getCity(), trip.getDataAnd()
                    , trip.getDataRit(), trip.getPrice(), trip.getImage(), trip.getId());
            viaggi.add(tripBean);
        }
        return viaggi;
    }

    public void bookTrip(BookBean booking) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        TripDAO tripdao;
        tripdao=new TripDAO();
        UserDAO userdao;
        userdao=new UserDAO();
        EntityFactory utente;
        utente=userdao.execute(booking.getUsername());
        Trip trip;
        trip=tripdao.execute(booking.getTripId());
        UserTripStatus userTripStatus = new UserTripStatus(utente.getUsername());
        UserTrip usertrip = new UserTrip(userTripStatus,trip.getId());

        if(WRITER.equals("dbms")){
        BookingDAOdbms usertripdao = new BookingDAOdbms();

        try{
            usertripdao.setTripBook(usertrip);
        }
        catch(AlreadyPrenotedException e){
            throw new AlreadyPrenotedException(e.getMessage());
        }
        }
        else{
            BookingDAOcsv bookingDAOcsv=new BookingDAOcsv();
            try{
                bookingDAOcsv.setTripBook(usertrip);
            }
            catch(AlreadyPrenotedException e){
                throw new AlreadyPrenotedException(e.getMessage());
            }
        }

        if (trip.getAvailable() > 0) {

            tripdao.refreshAvailable(trip.getId());

        } else {

            throw new PlacesTerminatedException("i posti per il viaggio sono terminati");
        }



    }



    public List<TripBean> getTripUser(UserBean utente) throws SQLException, IOException {
        TripDAO tripDAO;
        tripDAO=new TripDAO();
        List<Trip> trip;
        trip=tripDAO.tripUser(utente.getUsername());
        List<TripBean> tripBeanList;
        tripBeanList=new ArrayList<>();
        for (Trip value : trip) {

            TripBean tripBean;
            tripBean=new TripBean(value.getCity(), value.getAvailable(), value.getDataAnd(), value.getDataRit(), value.getPrice(), value.getImage(), value.isStato());
            tripBeanList.add(tripBean);
        }
        return tripBeanList;
    }
    public List<TripBean> searchByCity(SearchBean searchBean) throws SQLException, IOException, FailedSearchException {
        TripDAO tripdao;
        tripdao=new TripDAO();
        List<TripBean> trips;
        trips=new ArrayList<>();
        List<Trip> viaggi;
        viaggi=tripdao.searchTrip(searchBean.getCitta());
        if(viaggi.isEmpty()){
            throw new FailedSearchException("Nessun itinerario disponibile per questa città");
        }
        for (Trip trip:viaggi) {

            TripBean tripBean;
            tripBean=new TripBean(trip.getAvailable(), trip.getCity(), trip.getDataAnd(), trip.getDataRit(), trip.getPrice(), trip.getImage(), trip.getId());
            trips.add(tripBean);
        }
        return trips;
    }
}


