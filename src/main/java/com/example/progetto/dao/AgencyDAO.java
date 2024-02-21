package com.example.progetto.dao;

import com.example.progetto.pattern.factory.EntityFactory;
import com.example.progetto.pattern.factory.Factory;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AgencyDAO implements GenericDAO <EntityFactory> {
    private final Connectivity connection;
    public AgencyDAO() throws SQLException, IOException{
        connection = Connectivity.getSingletonInstance();

    }

@Override
public EntityFactory execute(Object... params) throws SQLException {
    String username = (String) params[0];
    EntityFactory utente;
    try (CallableStatement cs = connection.conn.prepareCall("{call GetPasswordAgenzia(?,?)}")) {
        cs.setString(1, username);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.executeQuery();
        Factory factory=new Factory();

        utente=factory.CreateEntity(2);
        utente.setPassword(cs.getString(2));
        utente.setUsername((String) params[0]);
    } // Il CallableStatement verrà chiuso automaticamente qui

    return utente;
}
}
