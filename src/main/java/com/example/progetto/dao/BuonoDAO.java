package com.example.progetto.dao;

import com.example.progetto.Exception.NotValidCouponException;
import com.example.progetto.bean.BuonoBean;
import com.example.progetto.model.Buono;
import com.example.progetto.model.User;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
public class BuonoDAO implements GenericDAO <Buono> {
    private final Connectivity connection;
    public BuonoDAO() throws SQLException, IOException {
        connection =Connectivity.getSingletonInstance();

    }

    @Override
    public Buono execute(Object... params) throws SQLException, NotValidCouponException {
        BuonoBean buonobean = (BuonoBean) params[0];
        Buono buono;
        try {
            CallableStatement cs = connection.conn.prepareCall("{call GetBuono(?,?)}");

            cs.setString(1, buonobean.getCodice());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeQuery();
            int n = cs.getInt(2);
            buono = new Buono(buonobean.getCodice(), cs.getInt(2));
            if (n == 0) {
                throw new NotValidCouponException("buono non trovato");
            }
        }
        catch (SQLException e){
            throw new SQLException("errore recupero buono"+e.getMessage());
        }

        return buono;
    }
}

