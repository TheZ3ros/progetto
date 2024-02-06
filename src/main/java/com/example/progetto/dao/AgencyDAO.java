package com.example.progetto.dao;

import com.example.progetto.entity.Agency;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AgencyDAO implements GenericDAO <Agency> {
    private final Connectivity connection;
    public AgencyDAO(){
        connection = Connectivity.getSingletonInstance();

    }

    @Override
    public Agency execute(Object... params) throws SQLException {
        String username = (String) params[0];
        Agency utente = new Agency();
        try {
            connection.connected();
            CallableStatement cs = connection.conn.prepareCall("{call GetPasswordAgenzia(?,?)}");
            try {
                cs.setString(1, username);
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.executeQuery();
                utente.setPassword(cs.getString(2));
                utente.setUser((String) params[0]);
            } catch (SQLException queryException) {
                // Gestisci l'eccezione se si verifica un errore durante l'esecuzione della query
                queryException.printStackTrace();
            } finally {
                // Chiudi lo statement
                if (cs != null) {
                    try {
                        cs.close();
                    } catch (SQLException e) {
                        // Gestisci l'eccezione se si verifica un errore durante la chiusura dello statement
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            // Chiudi la connessione
            if (connection != null && connection.conn != null) {
                try {
                    connection.conn.close();
                } catch (SQLException e) {
                    // Gestisci l'eccezione se si verifica un errore durante la chiusura della connessione
                    e.printStackTrace();
                }
            }
        }
        return utente;
    }

}
