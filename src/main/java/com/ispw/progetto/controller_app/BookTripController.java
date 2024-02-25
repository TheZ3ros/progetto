package com.ispw.progetto.controller_app;

import com.ispw.progetto.bean.SearchBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.dao.csv_dbms.BookingDAOcsv;
import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.exception.FailedSearchException;
import com.ispw.progetto.exception.PlacesTerminatedException;
import com.ispw.progetto.bean.BookBean;
import com.ispw.progetto.dao.TripDAO;
import com.ispw.progetto.dao.UserDAO;
import com.ispw.progetto.dao.csv_dbms.BookingDAOdbms;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.model.Trip;
import com.ispw.progetto.model.UserTrip;
import com.ispw.progetto.pattern.decorator.UserTripStatus;
import com.ispw.progetto.pattern.factory.EntityFactory;

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

    public void bookTrip(BookBean booking) throws SQLException, IOException,  AlreadyPrenotedException {
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


            tripdao.refreshAvailable(trip.getId());




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
    public void checkAlready(TripBean trip,UserBean user) throws SQLException, IOException, AlreadyPrenotedException, PlacesTerminatedException {
        String username= user.getUsername();
        int id=trip.getId();
        UserTripStatus userTripStatus=new UserTripStatus(username);
        UserTrip userTrip=new UserTrip(userTripStatus,id);
        BookingDAOdbms bookingDAOdbms=new BookingDAOdbms();
            try{
                bookingDAOdbms.alreadyExist(userTrip);
            }
            catch(AlreadyPrenotedException e){
                throw new AlreadyPrenotedException(e.getMessage());
            }
            if(trip.getAvailable()==0) {
                throw new PlacesTerminatedException("i posti per il viaggio sono terminati");
            }

    }

}


