package com.example.progetto.dao;
import com.example.progetto.entity.User;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class UserDAO implements GenericDAO <User> {
    private final Connectivity connection;
    public UserDAO(){
        connection =Connectivity.getSingletonInstance();

    }

    @Override
    public User execute(Object... params) throws SQLException {
        String username = (String) params[0];
        User utente=new User();
        connection.connected();
        CallableStatement cs = connection.conn.prepareCall("{call GetPassword(?,?)}");
        cs.setString(1,username);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.executeQuery();
        utente.setPassword(cs.getString(2));
        utente.setUser((String) params[0]);


        return utente;
    }
}
