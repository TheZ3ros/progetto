package com.example.progetto.DAO;

import java.sql.Date;
import java.sql.SQLException;
import com.example.progetto.entity.TripCreation;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class TripCreationDAO {
    private final Connectivity connection;
    public TripCreationDAO(){
        connection = Connectivity.getSingletonInstance();
    }

    public void add_trip(String city, int available, LocalDate data_and,LocalDate data_rit,float price,String image_path) throws SQLException {
        connection.connected();
        CallableStatement cs = connection.conn.prepareCall("{call AddTrip(?,?,?,?,?,?)}");
        cs.setString(1,city);
        cs.setInt(2,available);
        cs.setDate(3, Date.valueOf(data_and));
        cs.setDate(4, Date.valueOf(data_rit));
        cs.setFloat(5,price);
        cs.setString(6,image_path);
        cs.executeQuery();
        System.out.println("Query eseguita");
    }

}
