package com.example.progetto.dao;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.SQLStatementException;
import com.example.progetto.model.User;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class UserDAO implements GenericDAO <User> {
    private final Connectivity connection;
    public UserDAO() throws SQLException, IOException {
        connection =Connectivity.getSingletonInstance();

    }

    @Override
    public User execute(Object... params) throws SQLException {
        String username = (String) params[0];
        User utente=new User();
        try(CallableStatement cs = connection.conn.prepareCall("{call GetPassword(?,?)}")) {
            cs.setString(1, username);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeQuery();
            utente.setPassword(cs.getString(2));
            utente.setUser((String) params[0]);
        }

        return utente;
    }
    public void registrazione(User user) throws ExistsUserException, SQLException, SQLStatementException {
        checkUser(user);
        String username=user.getUsername();
        String password= user.getPassword();
        try(CallableStatement cs = connection.conn.prepareCall("{call SetUser(?,?)}")) {
            cs.setString(1, username);
            cs.setString(2, password);
            cs.executeQuery();
        } catch (SQLException e) {
            throw new SQLStatementException("Errore nell'esecuzione dello statement SQL");
        }

    }

    public void checkUser(User user) throws ExistsUserException, SQLException {
        try (CallableStatement cs = connection.conn.prepareCall("{call CheckUser(?,?)}")){

            cs.setString(1, user.getUsername());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeQuery();
            int n = cs.getInt(2);
            if (n == 1) {
                throw new ExistsUserException("utente già esistente");
            }
        }
        catch (SQLException e){
            throw new SQLException("errore username"+e.getMessage());
        }


    }
    }

